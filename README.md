# template-json

json template use json to json

##### 主要功能： 根据模板对数据进行转换

##### 支持的语法： if、list、展开符“...”

##### 数据转换函数： case 、 substring 、 dateformat

> 使用举例子


引入jar

```xml

<dependency>
    <groupId>io.github.79121262</groupId>
    <artifactId>template-json</artifactId>
    <version>0.1.1</version>
</dependency>
```

定义模板：

```json 
{
  "x-if": {
    "expression": "1==1",
    "key": "re",
    "value": {
      "code": "${data.code}"
    }
  },
  "x-list": {
    "expression": "item as data.childBankList",
    "key": "childBankList",
    "value": {
      "companyName": "${item.bankBranchName}",
      "createTime": "${item.createTime}(dateformat[yyyy-MM-dd HH:mm:ss,yyyy-MM])"
    }
  },
  "accountNumbers": [
    {
      "reserved": "x-list",
      "expression": "item as data.childBankList",
      "value":  "${item.accountNumber}"
    }
  ],
  "type": ["...data.type"],
  "${data.childBankList[1].companyName}": "${data.childBankList[1].currency}",
  "parentAccount": "${data.parentAccount}",
  "...data.parentBank": "..."
}
```

例如需要转换的数据：

```json
{
  "code": "XY202103240002",
  "parentAccount": "77120122000016235",
  "type": [
    1,
    2,
    3,
    4
  ],
  "parentBranch": "xxxxx科技支行 ZONG行",
  "childBankList": [
    {
      "bankBranchName": "xxxx科技支行",
      "companyName": "xxxx有限公司",
      "accountNumber": "77120122000016388",
      "createTime": "2021-03-24 13:56:12",
      "currency": "JPS",
      "id": "267"
    },
    {
      "bankBranchName": "XFAE 支行",
      "companyName": "xxxx有限公司1",
      "accountNumber": "77120122000016500",
      "createTime": "2021-03-24 13:56:12",
      "currency": "CNY",
      "id": "268"
    }
  ],
  "parentBank": {
    "bankBranchName": "xxxxx淀科技支行",
    "companyName": "xxxxx广告有限公司",
    "accountNumber": "77120122000016235"
  }
}
```

#### 使用示例

```java

public class ObjectTest {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ObjectTest.class.getClassLoader();
        byte[] tpl = Files.readAllBytes(Paths.get(loader.getResource("模板.json").toURI()));
        byte[] source = Files.readAllBytes(Paths.get(loader.getResource("需要转换的数据.json").toURI()));

        ObjectMapper mapper = new ObjectMapper();
        Object tempJson = mapper.readValue(tpl, Object.class);
        Object root = mapper.readValue(source, Object.class);
        Parse parse = new Parse(new DefaultExtractor(root));
        Object data = parse.parse(tempJson, root);

        //out put json
        System.out.println(mapper.writeValueAsString(data));

        //System.out.println(JSON.toJSONString("ABC"));
    }
}

```

#### 输出示例

```json
{
  "accountNumbers": [
    "77120122000016388",
    "77120122000016500"
  ],
  "re": {
    "code": "XY202103240002"
  },
  "childBankList": [
    {
      "createTime": "2021-03",
      "companyName": "xxxx科技支行"
    },
    {
      "createTime": "2021-03",
      "companyName": "XFAE 支行"
    }
  ],
  "bankBranchName": "xxxxx淀科技支行",
  "companyName": "xxxxx广告有限公司",
  "xxxx有限公司1": "CNY",
  "type": [
    1,
    2,
    3,
    4
  ],
  "parentAccount": "77120122000016235",
  "accountNumber": "77120122000016235"
}

```

