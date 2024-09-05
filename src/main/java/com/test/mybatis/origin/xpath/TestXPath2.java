package com.test.mybatis.origin.xpath;

import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;

import java.util.List;
import java.util.Properties;

import static com.test.mybatis.origin.xpath.parser.TestXPathParser.getDocument;

/**
 * @author stone
 * @date 2024-09-05
 * @Description: ʹ�� MyBatis ������� xml �ļ����н���
 */
public class TestXPath2 {

    public static void main(String[] args) throws Exception {

        //XMLMapperEntityResolver resolver = new XMLMapperEntityResolver();
        XPathParser xPathParser = new XPathParser(getDocument("src/main/java/com/test/mybatis/origin/xpath/resource/inventory.xml"));
        //XPathParser�Ľ�����������: evalNode
        List<XNode> xNodes = xPathParser.evalNodes("//book[author='Neal Stephenson']/title/text()");
        System.out.println("��ѯ����ΪNeal Stephenson��ͼ�����: ");
        for (XNode xNode : xNodes) {
            System.out.println(xNode.getStringBody());
        }
        System.out.println("=================================");
        Properties properties = new Properties();
        properties.setProperty("price","7.50");
        xPathParser.setVariables(properties);
        List<XNode> xNodes2 = xPathParser.evalNodes("//book[author='Neal Stephenson']/price/text()");
        for (XNode xNode : xNodes2) {
            System.out.println(xNode.getStringBody());
        }

    }


}
