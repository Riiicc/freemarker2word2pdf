## 项目说明
#### 1.使用Freemarker模板导出word文档
WordGeneratorWithFreemarker.java

生成word文档位置：d:\\temp.doc

#### 2.使用documents4j 完成word文档转换为pdf（需要windows+Office环境）
ExportWordUtil.java
生成word文档位置：d:\\tempx.pdf


### 关于导出word文档的一些经验

- 图片使用图片文件的base64插入代码中，生成模板文件时需要预先在图片位置放置**不同图片**占位   
- 图片部分属性可以通过style进行设置  

```
// 回车标志

 <w:p w:rsidR="0014583E" w:rsidRDefault="0014583E">
    <w:pPr>
        <w:rPr>
            <w:sz w:val="24"/>
        </w:rPr>
    </w:pPr>
</w:p>
``` 
- 针对富文本内容的格式识别（富文本回车段落格式），通过freemarker自带函数replace替换富文本内容中的换行符（一般为/r/n或者/n）进行拼接
```
${ll.discussRec?replace("\r\n","</w:t></w:r></w:p><w:p w:rsidR=\"0025476F\" w:rsidRDefault=\"008B65AE\"><w:pPr><w:snapToGrid w:val=\"0\"/><w:spacing w:line=\"200\" w:lineRule=\"atLeast\"/><w:rPr><w:sz w:val=\"24\"/></w:rPr></w:pPr><w:r w:rsidR=\"00F97FE8\"><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:sz w:val=\"24\"/></w:rPr><w:t>")}

```
```
### 页边距设置

<w:pgMar w:top="851" w:right="1797" w:bottom="1418" w:left="1797" w:header="851" w:footer="992" w:gutter="0"/>
```
