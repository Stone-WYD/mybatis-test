package com.test.mybatis.origin.xpath;

import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;

import java.util.List;
import java.util.Properties;

import static com.test.mybatis.origin.xpath.parser.TestXPathParser.getDocument;

/**
 * @author stone
 * @date 2024-09-05
 * @Description: 使用 MyBatis 工具类对 xml 文件进行解析
 */
public class TestXPath2 {

    public static void main(String[] args) throws Exception {

        //XMLMapperEntityResolver resolver = new XMLMapperEntityResolver();
        XPathParser xPathParser = new XPathParser(getDocument("src/main/java/com/test/mybatis/origin/xpath/resource/inventory.xml"));
        //XPathParser的解析方法测试: evalNode
        List<XNode> xNodes = xPathParser.evalNodes("//book[author='Neal Stephenson']/title/text()");
        System.out.println("查询作者为Neal Stephenson的图书标题: ");
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
