package com.test.mybatis.origin.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class TestXPath {
    public static void main(String[] args) throws Exception {
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
        Document doc = documentBuilder.parse("src/main/java/com/test/mybatis/origin/xpath/resource/inventory.xml");

        //获取Xpath对象
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        XPathExpression expr = xPath.compile("//book[author='Neal Stephenson']/title/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        System.out.println("查询作者为Neal Stephenson的图书标题: ");
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        //mytest
        NodeList authors = (NodeList) xPath.compile("//book[title='Burning Tower']/author/text()").evaluate(doc, XPathConstants.NODESET);
        System.out.println("Burning Tower的作者为: ");
        for (int i = 0; i < authors.getLength(); i++) {
            System.out.println(authors.item(i).getNodeValue());
        }

        System.out.println("查询1997年后的图书属性和标题");
         nodes =(NodeList) xPath.evaluate("//book[@year>1997]/@*|//book[@year>1997]/title/text()", doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

    }
}
