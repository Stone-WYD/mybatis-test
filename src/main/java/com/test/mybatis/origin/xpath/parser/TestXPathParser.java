package com.test.mybatis.origin.xpath.parser;

import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;
import java.util.Properties;

//测试mybatis对XPath的封装工具类
public class TestXPathParser {

    public static void main(String[] args) throws Exception {

        //XMLMapperEntityResolver resolver = new XMLMapperEntityResolver();
        XPathParser xPathParser = new XPathParser(getDocument("src/main/resources/sqlMapConfig.xml"));
        //XPathParser的解析方法测试: evalNode
        List<XNode> xNodes = xPathParser.evalNodes("//mappers/mapper");
        for (XNode xNode : xNodes) {
            String resource = xNode.getStringAttribute("resource");
            System.out.println(resource);
        }
        //evalString
        System.out.println("-------------------------------------");
        Properties properties = new Properties();
        properties.setProperty("mapper1","sqlmap/User.xml");
        properties.setProperty("mapper2","sqlmap/User.xml");
        xPathParser.setVariables(properties);
        //String s = xPathParser.evalString("//mappers/mapper[@resource='sqlmap/User.xml']/@resource");
        String s = xPathParser.evalString("//mappers/mapper/@resource");
        System.out.println(s);
        //其中会有处理${}占位符的操作：PropertyParser.parse()
        /*
        * GenericTokenParser负责寻找和拼接String (带有 "${" , "}"的内容会交给TokenHandler处理)
        * TokenHandler负责处理"${}"中的内容
        * */

    }

    public static Document getDocument(String xml) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        //开启验证
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);

        //创建DocumentBuilder
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //设置异常处理对象
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("warning: " + exception.getMessage() );
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("error: " + exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("fatalError: " + exception.getMessage());
            }
        });

        //将文档加载到一个 Document 对象中
        Document doc = documentBuilder.parse(xml);

        return doc;
    }

}
