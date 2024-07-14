package javaricci.com.br;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class XMLReaderGUI extends JFrame {

    private JTextField txtNomePrefeitura, txtTipoNota, txtCodigoAutenticacao, txtNotaCancelada, txtDataCancelamento,
            txtNumeroNota, txtDataEmissao, txtOptIncentivoFiscal, txtDadosPrestadorTipoDocPrestador, txtDadosPrestadorDocumento,
            txtDadosPrestadorInscricaoMunicipal, txtDadosPrestadorRazaoSocial, txtDadosPrestadorEndereco,
            txtDadosPrestadorNumeroEndereco, txtDadosPrestadorBairro, txtDadosPrestadorMunicipio, txtDadosPrestadorUf,
            txtDadosPrestadorCep, txtDadosPrestadorPais, txtDadosPrestadorEmail, txtDadosTomadorTipoDoc,
            txtDadosTomadorDocumento, txtDadosTomadorInscricaoEstadual, txtDadosTomadorRazaoSocial, txtDadosTomadorEndereco,
            txtDadosTomadorNumeroEndereco, txtDadosTomadorComplemento, txtDadosTomadorBairro, txtDadosTomadorCidade,
            txtDadosTomadorUf, txtDadosTomadorCep, txtDadosTomadorEmail, txtDadosTomadorPais, txtLocalServicoPais,
            txtLocalServicoCidade, txtLocalServicoUf, txtDetalhesServicoDescricao, txtDetalhesServicoValorServico,
            txtDetalhesServicoCodigo, txtDetalhesServicoAtividade, txtDetalhesServicoAliquota, txtDetalhesServicoInss,
            txtDetalhesServicoIrrf, txtDetalhesServicoCsll, txtDetalhesServicoCofins, txtDetalhesServicoPisPasep,
            txtDetalhesServicoOutra, txtDetalhesServicoIssRetido, txtDetalhesServicoOutroMunicipio, txtDetalhesServicoObservacao,
            txtTotaisDescontoCondicional, txtTotaisDescontoIncondicional, txtTotaisDeducaoMaterial, txtTotaisValorDeducao,
            txtTotaisBaseCalculo, txtTotaisValorIss, txtTotaisValorLiquidoNota;

    private JButton btnSelectFile, btnExportCSV, btnCreateDatabase, btnSaveToDatabase;
    private File selectedFile;

    public XMLReaderGUI() {
        setTitle("Ler Arquivo XML e Exportar Para *.CSV");
        setSize(900, 600); //900=LARGURA 600=ALTURA
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(30, 2)); //QTD Linhas e QTD de Colunas

        // Inicializando os campos de texto e os botões
        initUI();
    }

    private void initUI() {
        // Campos de texto para NfeCabecario
        txtNomePrefeitura = createTextField("Nome Prefeitura");
        txtTipoNota = createTextField("Tipo Nota");
        txtCodigoAutenticacao = createTextField("Código Autenticação");
        txtNotaCancelada = createTextField("Nota Cancelada");
        txtDataCancelamento = createTextField("Data Cancelamento");
        txtNumeroNota = createTextField("Número Nota");
        txtDataEmissao = createTextField("Data Emissão");
        txtOptIncentivoFiscal = createTextField("Opt Incentivo Fiscal");

        // Campos de texto para DadosPrestador
        txtDadosPrestadorTipoDocPrestador = createTextField("Tipo Documento (Prestador)");
        txtDadosPrestadorDocumento = createTextField("Documento (Prestador)");
        txtDadosPrestadorInscricaoMunicipal = createTextField("Inscrição Municipal (Prestador)");
        txtDadosPrestadorRazaoSocial = createTextField("Razão Social (Prestador)");
        txtDadosPrestadorEndereco = createTextField("Endereço (Prestador)");
        txtDadosPrestadorNumeroEndereco = createTextField("Número Endereço (Prestador)");
        txtDadosPrestadorBairro = createTextField("Bairro (Prestador)");
        txtDadosPrestadorMunicipio = createTextField("Município (Prestador)");
        txtDadosPrestadorUf = createTextField("UF (Prestador)");
        txtDadosPrestadorCep = createTextField("CEP (Prestador)");
        txtDadosPrestadorPais = createTextField("País (Prestador)");
        txtDadosPrestadorEmail = createTextField("Email (Prestador)");

        // Campos de texto para DadosTomador
        txtDadosTomadorTipoDoc = createTextField("Tipo Documento (Tomador)");
        txtDadosTomadorDocumento = createTextField("Documento (Tomador)");
        txtDadosTomadorInscricaoEstadual = createTextField("Inscrição Estadual (Tomador)");
        txtDadosTomadorRazaoSocial = createTextField("Razão Social (Tomador)");
        txtDadosTomadorEndereco = createTextField("Endereço (Tomador)");
        txtDadosTomadorNumeroEndereco = createTextField("Número Endereço (Tomador)");
        txtDadosTomadorComplemento = createTextField("Complemento (Tomador)");
        txtDadosTomadorBairro = createTextField("Bairro (Tomador)");
        txtDadosTomadorCidade = createTextField("Cidade (Tomador)");
        txtDadosTomadorUf = createTextField("UF (Tomador)");
        txtDadosTomadorCep = createTextField("CEP (Tomador)");
        txtDadosTomadorEmail = createTextField("Email (Tomador)");
        txtDadosTomadorPais = createTextField("País (Tomador)");

        // Campos de texto para LocalServico
        txtLocalServicoPais = createTextField("País (Local do Serviço)");
        txtLocalServicoCidade = createTextField("Cidade (Local do Serviço)");
        txtLocalServicoUf = createTextField("UF (Local do Serviço)");

        // Campos de texto para DetalhesServico
        txtDetalhesServicoDescricao = createTextField("Descrição (Serviço)");
        txtDetalhesServicoValorServico = createTextField("Valor Serviço");
        txtDetalhesServicoCodigo = createTextField("Código Serviço");
        txtDetalhesServicoAtividade = createTextField("Atividade");
        txtDetalhesServicoAliquota = createTextField("Alíquota");
        txtDetalhesServicoInss = createTextField("INSS");
        txtDetalhesServicoIrrf = createTextField("IRRF");
        txtDetalhesServicoCsll = createTextField("CSLL");
        txtDetalhesServicoCofins = createTextField("Cofins");
        txtDetalhesServicoPisPasep = createTextField("PIS/Pasep");
        txtDetalhesServicoOutra = createTextField("Outra");
        txtDetalhesServicoIssRetido = createTextField("ISS Retido");
        txtDetalhesServicoOutroMunicipio = createTextField("Outro Município");
        txtDetalhesServicoObservacao = createTextField("Observação");

        // Campos de texto para Totais
        txtTotaisDescontoCondicional = createTextField("Desconto Condicional");
        txtTotaisDescontoIncondicional = createTextField("Desconto Incondicional");
        txtTotaisDeducaoMaterial = createTextField("Deduções Material");
        txtTotaisValorDeducao = createTextField("Valor Deduções");
        txtTotaisBaseCalculo = createTextField("Base de Cálculo");
        txtTotaisValorIss = createTextField("Valor ISS");
        txtTotaisValorLiquidoNota = createTextField("Valor Líquido Nota");

        // Botão para selecionar arquivo XML
        btnSelectFile = new JButton("Selecionar Arquivo XML");
        btnSelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    parseXML(selectedFile);
                }
            }
        });
        add(btnSelectFile);

        // Botão Exportar Arquivo *.CSV
        btnExportCSV = new JButton("Exportar B.Dados CSV");
        btnExportCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToCSV();
            }
        });
        add(btnExportCSV);

        // Botão Criar Data Base e Tabelas no Banco de Dados SQLite
        btnCreateDatabase = new JButton("Criar Banco Dados SQLite");
        btnCreateDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDatabase();
            }
        });
        add(btnCreateDatabase);

        

        // Botão Salvar Dados no Banco de Dados SQLite
        btnSaveToDatabase = new JButton("Salvar Dados Banco Dados");
        btnSaveToDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToDatabase();
            }
        });
        add(btnSaveToDatabase);
    }

    

    private JTextField createTextField(String label) {
        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(Color.BLUE); //Cor da letra no JLabel
        JTextField textField = new JTextField();
        add(jLabel);
        add(textField);
        return textField;
    }



    private void parseXML(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // Ler elementos XML e preencher campos de texto

            // NfeCabecario
            txtNomePrefeitura.setText(getTagValue(doc, "NfeCabecario", "prefeitura"));
            txtTipoNota.setText(getTagValue(doc, "NfeCabecario", "tipoNota"));
            txtCodigoAutenticacao.setText(getTagValue(doc, "NfeCabecario", "codigoAutenticacao"));
            txtNotaCancelada.setText(getTagValue(doc, "NfeCabecario", "notaCancelada"));
            txtDataCancelamento.setText(getTagValue(doc, "NfeCabecario", "dataCancelamento"));
            txtNumeroNota.setText(getTagValue(doc, "NfeCabecario", "numeroNota"));
            txtDataEmissao.setText(getTagValue(doc, "NfeCabecario", "dataEmissao"));
            txtOptIncentivoFiscal.setText(getTagValue(doc, "NfeCabecario", "optIncentivoFiscal"));

            // DadosPrestador
            txtDadosPrestadorTipoDocPrestador.setText(getTagValue(doc, "DadosPrestador", "tipoDocPrestador"));
            txtDadosPrestadorDocumento.setText(getTagValue(doc, "DadosPrestador", "documento"));
            txtDadosPrestadorInscricaoMunicipal.setText(getTagValue(doc, "DadosPrestador", "inscricaoMunicipal"));
            txtDadosPrestadorRazaoSocial.setText(getTagValue(doc, "DadosPrestador", "razaoSocial"));
            txtDadosPrestadorEndereco.setText(getTagValue(doc, "DadosPrestador", "endereco"));
            txtDadosPrestadorNumeroEndereco.setText(getTagValue(doc, "DadosPrestador", "numeroEndereco"));
            txtDadosPrestadorBairro.setText(getTagValue(doc, "DadosPrestador", "bairro"));
            txtDadosPrestadorMunicipio.setText(getTagValue(doc, "DadosPrestador", "municipio"));
            txtDadosPrestadorUf.setText(getTagValue(doc, "DadosPrestador", "uf"));
            txtDadosPrestadorCep.setText(getTagValue(doc, "DadosPrestador", "cep"));
            txtDadosPrestadorPais.setText(getTagValue(doc, "DadosPrestador", "pais"));
            txtDadosPrestadorEmail.setText(getTagValue(doc, "DadosPrestador", "email"));

            // DadosTomador
            txtDadosTomadorTipoDoc.setText(getTagValue(doc, "DadosTomador", "tipodoc"));
            txtDadosTomadorDocumento.setText(getTagValue(doc, "DadosTomador", "documento"));
            txtDadosTomadorInscricaoEstadual.setText(getTagValue(doc, "DadosTomador", "inscricaoEstadual"));
            txtDadosTomadorRazaoSocial.setText(getTagValue(doc, "DadosTomador", "razaoSocial"));
            txtDadosTomadorEndereco.setText(getTagValue(doc, "DadosTomador", "endereco"));
            txtDadosTomadorNumeroEndereco.setText(getTagValue(doc, "DadosTomador", "numeroEndereco"));
            txtDadosTomadorComplemento.setText(getTagValue(doc, "DadosTomador", "complemento"));
            txtDadosTomadorBairro.setText(getTagValue(doc, "DadosTomador", "bairro"));
            txtDadosTomadorCidade.setText(getTagValue(doc, "DadosTomador", "cidade"));
            txtDadosTomadorUf.setText(getTagValue(doc, "DadosTomador", "uf"));
            txtDadosTomadorCep.setText(getTagValue(doc, "DadosTomador", "cep"));
            txtDadosTomadorEmail.setText(getTagValue(doc, "DadosTomador", "email"));
            txtDadosTomadorPais.setText(getTagValue(doc, "DadosTomador", "pais"));

            // LocalServico
            txtLocalServicoPais.setText(getTagValue(doc, "localServico", "pais"));
            txtLocalServicoCidade.setText(getTagValue(doc, "localServico", "cidade"));
            txtLocalServicoUf.setText(getTagValue(doc, "localServico", "uf"));

            // DetalhesServico
            txtDetalhesServicoDescricao.setText(getTagValue(doc, "DetalhesServico", "descricao"));
            txtDetalhesServicoValorServico.setText(getTagValue(doc, "DetalhesServico", "valorServico"));
            txtDetalhesServicoCodigo.setText(getTagValue(doc, "DetalhesServico", "codigo"));
            txtDetalhesServicoAtividade.setText(getTagValue(doc, "DetalhesServico", "atividade"));
            txtDetalhesServicoAliquota.setText(getTagValue(doc, "DetalhesServico", "aliquota"));
            txtDetalhesServicoInss.setText(getTagValue(doc, "DetalhesServico", "inss"));
            txtDetalhesServicoIrrf.setText(getTagValue(doc, "DetalhesServico", "ir"));
            txtDetalhesServicoCsll.setText(getTagValue(doc, "DetalhesServico", "csll"));
            txtDetalhesServicoCofins.setText(getTagValue(doc, "DetalhesServico", "cofins"));
            txtDetalhesServicoPisPasep.setText(getTagValue(doc, "DetalhesServico", "pispasep"));
            txtDetalhesServicoOutra.setText(getTagValue(doc, "DetalhesServico", "outra"));
            txtDetalhesServicoIssRetido.setText(getTagValue(doc, "DetalhesServico", "issretido"));
            txtDetalhesServicoOutroMunicipio.setText(getTagValue(doc, "DetalhesServico", "outromunicipio"));
            txtDetalhesServicoObservacao.setText(getTagValue(doc, "DetalhesServico", "obs"));

            // Totais
            txtTotaisDescontoCondicional.setText(getTagValue(doc, "Totais", "descontoCondicional"));
            txtTotaisDescontoIncondicional.setText(getTagValue(doc, "Totais", "descontoIncondicional"));
            txtTotaisDeducaoMaterial.setText(getTagValue(doc, "Totais", "deducaoMaterial"));
            txtTotaisValorDeducao.setText(getTagValue(doc, "Totais", "valorDeducao"));
            txtTotaisBaseCalculo.setText(getTagValue(doc, "Totais", "baseCalculo"));
            txtTotaisValorIss.setText(getTagValue(doc, "Totais", "valorIss"));
            txtTotaisValorLiquidoNota.setText(getTagValue(doc, "Totais", "valorLiquidoNota"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getTagValue(Document doc, String parentTag, String childTag) {
        NodeList nodeList = doc.getElementsByTagName(parentTag);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                return element.getElementsByTagName(childTag).item(0).getTextContent();
            }
        }
        return null;
    }



    /*
    //ESSE MÉTODO EXPORTA PARA *.CSV SOMENTE O CAMPOS QUE ESTÃO NA TELA JTEXTFIELD
    private void exportToCSV() {
        try (FileWriter writer = new FileWriter("dados.csv")) {
            // Escrevendo cabeçalho do CSV
            writer.append("Nome Prefeitura; Tipo Nota; Código Autenticação; Nota Cancelada; Data Cancelamento; Número Nota; Data Emissão; Opt Incentivo Fiscal; Tipo Documento (Prestador); Documento (Prestador); Inscrição Municipal (Prestador); Razão Social (Prestador); Endereço (Prestador); Número Endereço (Prestador); Bairro (Prestador); Município (Prestador); UF (Prestador); CEP (Prestador); País (Prestador); Email (Prestador); Tipo Documento (Tomador); Documento (Tomador); Inscrição Estadual (Tomador); Razão Social (Tomador); Endereço (Tomador); Número Endereço (Tomador); Complemento (Tomador); Bairro (Tomador); Cidade (Tomador); UF (Tomador); CEP (Tomador); Email (Tomador); País (Tomador); País (Local do Serviço); Cidade (Local do Serviço); UF (Local do Serviço); Descrição (Serviço); Valor Serviço; Código Serviço; Atividade; Alíquota; INSS; IRRF; CSLL; Cofins; PIS/Pasep; Outra; ISS Retido; Outro Município; Observação; Desconto Condicional; Desconto Incondicional; Deduções Material; Valor Deduções; Base de Cálculo; Valor ISS; Valor Líquido Nota\n");

            // Escrevendo dados
            writer.append(txtNomePrefeitura.getText()).append("; ")
            .append(txtTipoNota.getText()).append("; ")
            .append(txtCodigoAutenticacao.getText()).append("; ")
            .append(txtNotaCancelada.getText()).append("; ")
            .append(txtDataCancelamento.getText()).append("; ")
            .append(txtNumeroNota.getText()).append("; ")
            .append(txtDataEmissao.getText()).append("; ")
            .append(txtOptIncentivoFiscal.getText()).append("; ")
            .append(txtDadosPrestadorTipoDocPrestador.getText()).append("; ")
            .append(txtDadosPrestadorDocumento.getText()).append("; ")
            .append(txtDadosPrestadorInscricaoMunicipal.getText()).append("; ")
            .append(txtDadosPrestadorRazaoSocial.getText()).append("; ")
            .append(txtDadosPrestadorEndereco.getText()).append("; ")
            .append(txtDadosPrestadorNumeroEndereco.getText()).append("; ")
            .append(txtDadosPrestadorBairro.getText()).append("; ")
            .append(txtDadosPrestadorMunicipio.getText()).append("; ")
            .append(txtDadosPrestadorUf.getText()).append("; ")
            .append(txtDadosPrestadorCep.getText()).append("; ")
            .append(txtDadosPrestadorPais.getText()).append("; ")
            .append(txtDadosPrestadorEmail.getText()).append("; ")
            .append(txtDadosTomadorTipoDoc.getText()).append("; ")
            .append(txtDadosTomadorDocumento.getText()).append("; ")
            .append(txtDadosTomadorInscricaoEstadual.getText()).append("; ")
            .append(txtDadosTomadorRazaoSocial.getText()).append("; ")
            .append(txtDadosTomadorEndereco.getText()).append("; ")
            .append(txtDadosTomadorNumeroEndereco.getText()).append("; ")
            .append(txtDadosTomadorComplemento.getText()).append("; ")
            .append(txtDadosTomadorBairro.getText()).append("; ")
            .append(txtDadosTomadorCidade.getText()).append("; ")
            .append(txtDadosTomadorUf.getText()).append("; ")
            .append(txtDadosTomadorCep.getText()).append("; ")
            .append(txtDadosTomadorEmail.getText()).append("; ")
            .append(txtDadosTomadorPais.getText()).append("; ")
            .append(txtLocalServicoPais.getText()).append("; ")
            .append(txtLocalServicoCidade.getText()).append("; ")
            .append(txtLocalServicoUf.getText()).append("; ")
            .append(txtDetalhesServicoDescricao.getText()).append("; ")
            .append(txtDetalhesServicoValorServico.getText()).append("; ")
            .append(txtDetalhesServicoCodigo.getText()).append("; ")
            .append(txtDetalhesServicoAtividade.getText()).append("; ")
            .append(txtDetalhesServicoAliquota.getText()).append("; ")
            .append(txtDetalhesServicoInss.getText()).append("; ")
            .append(txtDetalhesServicoIrrf.getText()).append("; ")
            .append(txtDetalhesServicoCsll.getText()).append("; ")
            .append(txtDetalhesServicoCofins.getText()).append("; ")
            .append(txtDetalhesServicoPisPasep.getText()).append("; ")
            .append(txtDetalhesServicoOutra.getText()).append("; ")
            .append(txtDetalhesServicoIssRetido.getText()).append("; ")
            .append(txtDetalhesServicoOutroMunicipio.getText()).append("; ")
            .append(txtDetalhesServicoObservacao.getText()).append("; ")
            .append(txtTotaisDescontoCondicional.getText()).append("; ")
            .append(txtTotaisDescontoIncondicional.getText()).append("; ")
            .append(txtTotaisDeducaoMaterial.getText()).append("; ")
            .append(txtTotaisValorDeducao.getText()).append("; ")
            .append(txtTotaisBaseCalculo.getText()).append("; ")
            .append(txtTotaisValorIss.getText()).append("; ")
            .append(txtTotaisValorLiquidoNota.getText()).append("\n");

            JOptionPane.showMessageDialog(this, "Dados exportados para CSV com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao exportar para CSV!");
        }
    }
    */
    
    
    //ESSE MÉTODO EXPORTA PARA *.CSV TUDO QUE ESTÁ GRAVADO NA TABELA NfeCabecario
    //DO BANCO DE DADOS SQLITE 
    private void exportToCSV() {
        String url = "jdbc:sqlite:nfseservico.db";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM NfeCabecario");
             FileWriter writer = new FileWriter("dados.csv")) {

            // Escrevendo cabeçalho do CSV
            writer.append("Nome Prefeitura;Tipo Nota;Código Autenticação;Nota Cancelada;Data Cancelamento;Número Nota;Data Emissão;Opt Incentivo Fiscal;");
            writer.append("Tipo Documento (Prestador);Documento (Prestador);Inscrição Municipal (Prestador);Razão Social (Prestador);Endereço (Prestador);Número Endereço (Prestador);Bairro (Prestador);Município (Prestador);UF (Prestador);CEP (Prestador);País (Prestador);Email (Prestador);");
            writer.append("Tipo Documento (Tomador);Documento (Tomador);Inscrição Estadual (Tomador);Razão Social (Tomador);Endereço (Tomador);Número Endereço (Tomador);Complemento (Tomador);Bairro (Tomador);Cidade (Tomador);UF (Tomador);CEP (Tomador);Email (Tomador);País (Tomador);");
            writer.append("País (Local do Serviço);Cidade (Local do Serviço);UF (Local do Serviço);");
            writer.append("Descrição (Serviço);Valor Serviço;Código Serviço;Atividade;Alíquota;INSS;IRRF;CSLL;Cofins;PIS/Pasep;Outra;ISS Retido;Outro Município;Observação;");
            writer.append("Desconto Condicional;Desconto Incondicional;Deduções Material;Valor Deduções;Base de Cálculo;Valor ISS;Valor Líquido Nota\n");

            // Escrevendo dados
            while (rs.next()) {
                writer.append(rs.getString("nfec_nomePrefeitura")).append(";")
                      .append(rs.getString("nfec_tipoNota")).append(";")
                      .append(rs.getString("nfec_codigoAutenticacao")).append(";")
                      .append(rs.getString("nfec_notaCancelada")).append(";")
                      .append(rs.getString("nfec_dataCancelamento")).append(";")
                      .append(rs.getString("nfec_numeroNota")).append(";")
                      .append(rs.getString("nfec_dataEmissao")).append(";")
                      .append(rs.getString("nfec_optIncentivoFiscal")).append(";")
                      .append(rs.getString("dpre_tipoDocPrestador")).append(";")
                      .append(rs.getString("dpre_documento")).append(";")
                      .append(rs.getString("dpre_inscricaoMunicipal")).append(";")
                      .append(rs.getString("dpre_razaoSocial")).append(";")
                      .append(rs.getString("dpre_endereco")).append(";")
                      .append(rs.getString("dpre_numeroEndereco")).append(";")
                      .append(rs.getString("dpre_bairro")).append(";")
                      .append(rs.getString("dpre_municipio")).append(";")
                      .append(rs.getString("dpre_uf")).append(";")
                      .append(rs.getString("dpre_cep")).append(";")
                      .append(rs.getString("dpre_pais")).append(";")
                      .append(rs.getString("dpre_email")).append(";")
                      .append(rs.getString("dtom_tipoDoc")).append(";")
                      .append(rs.getString("dtom_documento")).append(";")
                      .append(rs.getString("dtom_inscricaoEstadual")).append(";")
                      .append(rs.getString("dtom_razaoSocial")).append(";")
                      .append(rs.getString("dtom_endereco")).append(";")
                      .append(rs.getString("dtom_numeroEndereco")).append(";")
                      .append(rs.getString("dtom_complemento")).append(";")
                      .append(rs.getString("dtom_bairro")).append(";")
                      .append(rs.getString("dtom_cidade")).append(";")
                      .append(rs.getString("dtom_uf")).append(";")
                      .append(rs.getString("dtom_cep")).append(";")
                      .append(rs.getString("dtom_email")).append(";")
                      .append(rs.getString("dtom_pais")).append(";")
                      .append(rs.getString("lser_pais")).append(";")
                      .append(rs.getString("lser_cidade")).append(";")
                      .append(rs.getString("lser_uf")).append(";")
                      .append(rs.getString("dser_descricao")).append(";")
                      .append(rs.getString("dser_valorServico")).append(";")
                      .append(rs.getString("dser_codigo")).append(";")
                      .append(rs.getString("dser_atividade")).append(";")
                      .append(rs.getString("dser_aliquota")).append(";")
                      .append(rs.getString("dser_inss")).append(";")
                      .append(rs.getString("dser_irrf")).append(";")
                      .append(rs.getString("dser_csll")).append(";")
                      .append(rs.getString("dser_cofins")).append(";")
                      .append(rs.getString("dser_pisPasep")).append(";")
                      .append(rs.getString("dser_outra")).append(";")
                      .append(rs.getString("dser_issRetido")).append(";")
                      .append(rs.getString("dser_outroMunicipio")).append(";")
                      .append(rs.getString("dser_observacao")).append(";")
                      .append(rs.getString("tota_descontoCondicional")).append(";")
                      .append(rs.getString("tota_descontoIncondicional")).append(";")
                      .append(rs.getString("tota_deducaoMaterial")).append(";")
                      .append(rs.getString("tota_valorDeducao")).append(";")
                      .append(rs.getString("tota_baseCalculo")).append(";")
                      .append(rs.getString("tota_valorIss")).append(";")
                      .append(rs.getString("tota_valorLiquidoNota")).append("\n");
            }

            JOptionPane.showMessageDialog(this, "Dados exportados para CSV com sucesso!");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao exportar para CSV!");
        }
    }

    
    
    private void createDatabase() {
        String url = "jdbc:sqlite:nfseservico.db";

        String nfeCabecarioTable = "CREATE TABLE IF NOT EXISTS NfeCabecario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nfec_nomePrefeitura TEXT, nfec_tipoNota TEXT, nfec_codigoAutenticacao TEXT, nfec_notaCancelada TEXT, nfec_dataCancelamento TEXT, " +
                "nfec_numeroNota TEXT, nfec_dataEmissao TEXT, nfec_optIncentivoFiscal TEXT,"+

				"dpre_tipoDocPrestador TEXT, dpre_documento TEXT, dpre_inscricaoMunicipal TEXT, dpre_razaoSocial TEXT, dpre_endereco TEXT, " +
                "dpre_numeroEndereco TEXT, dpre_bairro TEXT, dpre_municipio TEXT, dpre_uf TEXT, dpre_cep TEXT, dpre_pais TEXT, dpre_email TEXT, " +
				
                "dtom_tipoDoc TEXT, dtom_documento TEXT, dtom_inscricaoEstadual TEXT, dtom_razaoSocial TEXT, dtom_endereco TEXT, " +
                "dtom_numeroEndereco TEXT, dtom_complemento TEXT, dtom_bairro TEXT, dtom_cidade TEXT, dtom_uf TEXT, dtom_cep TEXT, dtom_email TEXT, dtom_pais TEXT, " +
				
				"lser_pais TEXT, lser_cidade TEXT, lser_uf TEXT, " +
				
				"dser_descricao TEXT, dser_valorServico TEXT, dser_codigo TEXT, dser_atividade TEXT, dser_aliquota TEXT, dser_inss TEXT, dser_irrf TEXT, dser_csll TEXT, " +
                "dser_cofins TEXT, dser_pisPasep TEXT, dser_outra TEXT, dser_issRetido TEXT, dser_outroMunicipio TEXT, dser_observacao TEXT, "+
				
                "tota_descontoCondicional TEXT, tota_descontoIncondicional TEXT, tota_deducaoMaterial TEXT, tota_valorDeducao TEXT, " +
                "tota_baseCalculo TEXT, tota_valorIss TEXT, tota_valorLiquidoNota TEXT)";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(nfeCabecarioTable);

            JOptionPane.showMessageDialog(this, "Banco de dados e tabelas criados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao criar banco de dados!");
        }
    }



    private void saveToDatabase() {
        String url = "jdbc:sqlite:nfseservico.db";
        String sql = "INSERT INTO NfeCabecario (nfec_nomePrefeitura, nfec_tipoNota, nfec_codigoAutenticacao, nfec_notaCancelada, nfec_dataCancelamento, nfec_numeroNota, nfec_dataEmissao, nfec_optIncentivoFiscal, dpre_tipoDocPrestador, dpre_documento, dpre_inscricaoMunicipal, dpre_razaoSocial, dpre_endereco, dpre_numeroEndereco, dpre_bairro, dpre_municipio, dpre_uf, dpre_cep, dpre_pais, dpre_email, dtom_tipoDoc, dtom_documento, dtom_inscricaoEstadual, dtom_razaoSocial, dtom_endereco, dtom_numeroEndereco, dtom_complemento, dtom_bairro, dtom_cidade, dtom_uf, dtom_cep, dtom_email, dtom_pais, lser_pais, lser_cidade, lser_uf, dser_descricao, dser_valorServico, dser_codigo, dser_atividade, dser_aliquota, dser_inss, dser_irrf, dser_csll, dser_cofins, dser_pisPasep, dser_outra, dser_issRetido, dser_outroMunicipio, dser_observacao, tota_descontoCondicional, tota_descontoIncondicional, tota_deducaoMaterial, tota_valorDeducao, tota_baseCalculo, tota_valorIss, tota_valorLiquidoNota) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, txtNomePrefeitura.getText());
            pstmt.setString(2, txtTipoNota.getText());
            pstmt.setString(3, txtCodigoAutenticacao.getText());
            pstmt.setString(4, txtNotaCancelada.getText());
            pstmt.setString(5, txtDataCancelamento.getText());
            pstmt.setString(6, txtNumeroNota.getText());
            pstmt.setString(7, txtDataEmissao.getText());
            pstmt.setString(8, txtOptIncentivoFiscal.getText());
            pstmt.setString(9, txtDadosPrestadorTipoDocPrestador.getText());
            pstmt.setString(10, txtDadosPrestadorDocumento.getText());
            pstmt.setString(11, txtDadosPrestadorInscricaoMunicipal.getText());
            pstmt.setString(12, txtDadosPrestadorRazaoSocial.getText());
            pstmt.setString(13, txtDadosPrestadorEndereco.getText());
            pstmt.setString(14, txtDadosPrestadorNumeroEndereco.getText());
            pstmt.setString(15, txtDadosPrestadorBairro.getText());
            pstmt.setString(16, txtDadosPrestadorMunicipio.getText());
            pstmt.setString(17, txtDadosPrestadorUf.getText());
            pstmt.setString(18, txtDadosPrestadorCep.getText());
            pstmt.setString(19, txtDadosPrestadorPais.getText());
            pstmt.setString(20, txtDadosPrestadorEmail.getText());
            pstmt.setString(21, txtDadosTomadorTipoDoc.getText());
            pstmt.setString(22, txtDadosTomadorDocumento.getText());
            pstmt.setString(23, txtDadosTomadorInscricaoEstadual.getText());
            pstmt.setString(24, txtDadosTomadorRazaoSocial.getText());
            pstmt.setString(25, txtDadosTomadorEndereco.getText());
            pstmt.setString(26, txtDadosTomadorNumeroEndereco.getText());
            pstmt.setString(27, txtDadosTomadorComplemento.getText());
            pstmt.setString(28, txtDadosTomadorBairro.getText());
            pstmt.setString(29, txtDadosTomadorCidade.getText());
            pstmt.setString(30, txtDadosTomadorUf.getText());
            pstmt.setString(31, txtDadosTomadorCep.getText());
            pstmt.setString(32, txtDadosTomadorEmail.getText());
            pstmt.setString(33, txtDadosTomadorPais.getText());
            pstmt.setString(34, txtLocalServicoPais.getText());
            pstmt.setString(35, txtLocalServicoCidade.getText());
            pstmt.setString(36, txtLocalServicoUf.getText());
            pstmt.setString(37, txtDetalhesServicoDescricao.getText());
            pstmt.setString(38, txtDetalhesServicoValorServico.getText());
            pstmt.setString(39, txtDetalhesServicoCodigo.getText());
            pstmt.setString(40, txtDetalhesServicoAtividade.getText());
            pstmt.setString(41, txtDetalhesServicoAliquota.getText());
            pstmt.setString(42, txtDetalhesServicoInss.getText());
            pstmt.setString(43, txtDetalhesServicoIrrf.getText());
            pstmt.setString(44, txtDetalhesServicoCsll.getText());
            pstmt.setString(45, txtDetalhesServicoCofins.getText());
            pstmt.setString(46, txtDetalhesServicoPisPasep.getText());
            pstmt.setString(47, txtDetalhesServicoOutra.getText());
            pstmt.setString(48, txtDetalhesServicoIssRetido.getText());
            pstmt.setString(49, txtDetalhesServicoOutroMunicipio.getText());
            pstmt.setString(50, txtDetalhesServicoObservacao.getText());
            pstmt.setString(51, txtTotaisDescontoCondicional.getText());
            pstmt.setString(52, txtTotaisDescontoIncondicional.getText());
            pstmt.setString(53, txtTotaisDeducaoMaterial.getText());
            pstmt.setString(54, txtTotaisValorDeducao.getText());
            pstmt.setString(55, txtTotaisBaseCalculo.getText());
            pstmt.setString(56, txtTotaisValorIss.getText());
            pstmt.setString(57, txtTotaisValorLiquidoNota.getText());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void selectDirectoryAndParseXML() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File directory = chooser.getSelectedFile();
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".xml"));
            if (files != null) {
                for (File file : files) {
                    parseXML(file);
                }
            }
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            XMLReaderGUI app = new XMLReaderGUI();
            app.setVisible(true);
            app.selectDirectoryAndParseXML();
        });
    }
    
    
}
