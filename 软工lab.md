---
title: 软工lab v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.17"

---

# 软工lab

> v1.0.0

Base URLs:

* <a href="http://localhost:8443">http://localhost:8443: http://localhost:8443</a>

# e-shopping/用户

## POST 注册

POST /api/register

用户注册
400，409

> Body 请求参数

```json
{
  "username": "string",
  "password": "string",
  "phone": "string",
  "idCard": "string",
  "email": "string",
  "isSeller": true
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» username|body|string| 是 |none|
|» password|body|string| 是 |none|
|» phone|body|string| 是 |none|
|» idCard|body|string| 是 |none|
|» email|body|string| 是 |none|
|» isSeller|body|boolean| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|请求有误|Inline|

### 返回数据结构

状态码 **400**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» msg|string|true|none||none|

## POST 登录

POST /api/login

401

> Body 请求参数

```json
{
  "username": "string",
  "password": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» username|body|string| 是 |none|
|» password|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "isAdmin": true,
  "isSeller": true,
  "token": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|没有权限|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» isAdmin|boolean|true|none||none|
|» isSeller|boolean|true|none||none|
|» token|string|true|none||none|

## PUT 修改密码

PUT /api/user/profile/password

400，409

> Body 请求参数

```json
{
  "pastPassword": "string",
  "presentPassword": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» pastPassword|body|string| 是 |none|
|» presentPassword|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 用户账户充值

POST /api/user/account/recharge

400，404

> Body 请求参数

```json
{
  "amount": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» amount|body|number| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## DELETE 登出

DELETE /

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## PUT 修改用户名

PUT /api/user/profile/username

400，409

> Body 请求参数

```json
{
  "username": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» username|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## PUT 修改电子邮箱

PUT /api/user/profile/email

400，409

> Body 请求参数

```json
{
  "pastEmail": "string",
  "presentEmail": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» pastEmail|body|string| 是 |none|
|» presentEmail|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## PUT 修改手机号码

PUT /api/user/profile/phone

400，409

> Body 请求参数

```json
{
  "pastPhone": "string",
  "presentPhone": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» pastPhone|body|string| 是 |none|
|» presentPhone|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# e-shopping/商店

## POST 申请开店

POST /api/shop/apply

商户申请开店
400，403

> Body 请求参数

```json
{
  "shopName": "string",
  "tag": "string",
  "introduction": "string",
  "fund": 0,
  "idCard": "string",
  "address": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» shopName|body|string| 是 |none|
|» tag|body|string| 是 |none|
|» introduction|body|string| 是 |none|
|» fund|body|number| 是 |none|
|» idCard|body|string| 是 |none|
|» address|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 注销商店

POST /api/shop/cancel

403

> Body 请求参数

```json
{
  "reason": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» reason|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 商店账户充值（资金转移）

POST /api/shop/account/recharge

403，404，400

> Body 请求参数

```json
{
  "amount": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» amount|body|number| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## PUT 修改商店

PUT /api/shop/change

400，403

> Body 请求参数

```json
{
  "shopName": "string",
  "tag": "string",
  "introduction": "string",
  "address": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» shopName|body|string| 否 |none|
|» tag|body|string| 否 |none|
|» introduction|body|string| 否 |none|
|» address|body|string| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# e-shopping/商品

## POST 申请商品

POST /api/goods/apply

新增商品
400，403

> Body 请求参数

```yaml
picFile: file://C:\Users\26739\Desktop\EShopping.sql
price: "114.51"
description: desssssss
goodsName: goodsssssss
number: "114514"
tag: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» picFile|body|string(binary)| 否 |图片大小最大为1Mb|
|» price|body|number| 是 |none|
|» description|body|string| 是 |none|
|» goodsName|body|string| 是 |none|
|» number|body|integer| 是 |none|
|» tag|body|string| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## PUT 修改商品信息

PUT /api/goods/change

403，400

> Body 请求参数

```yaml
goodsId: "1"
price: "141.14"
description: 5sada6d4a5dsa4dsa
goodsName: da5sd5as4
number: "66"
picFile: file://C:\Users\26739\Desktop\新建文件夹\DB7489CCCCE2DF994EB347F4FAFEDFAB.jpg
tag: string

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» goodsId|body|integer| 否 |none|
|» price|body|number| 否 |none|
|» description|body|string| 否 |none|
|» goodsName|body|string| 否 |none|
|» number|body|integer| 否 |none|
|» picFile|body|string(binary)| 否 |none|
|» tag|body|string| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 删除商品

POST /api/goods/cancel

403

> Body 请求参数

```json
{
  "goodsId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» goodsId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# e-shopping/购物车

## POST 添加购物车

POST /api/cart/add

404

> Body 请求参数

```json
{
  "number": 0,
  "goodsId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» number|body|integer| 是 |none|
|» goodsId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 删除购物车

POST /api/cart/cancel

包括购买购物车商品时也要调用

> Body 请求参数

```json
{
  "cartId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» cartId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# e-shopping/管理

## POST 批准商店

POST /api/shop/approve

批准开店
404

> Body 请求参数

```json
{
  "shopId": 0,
  "isApproved": true
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» shopId|body|integer| 是 |none|
|» isApproved|body|boolean| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 商店批准展示

GET /api/shop/approve

批准开店 返回所有商店信息

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "shopId": 0,
    "userId": 0,
    "state": 0,
    "shopName": "string",
    "tag": "string",
    "introduction": "string",
    "address": "string",
    "fund": 0,
    "registerDate": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» shopId|integer|true|none||none|
|» userId|integer|true|none||none|
|» state|integer|true|none||none|
|» shopName|string|true|none||none|
|» tag|string|true|none||none|
|» introduction|string|true|none||none|
|» address|string|true|none||none|
|» fund|number|true|none||none|
|» registerDate|string|true|none||none|

## POST 批准商品

POST /api/goods/approve

404

> Body 请求参数

```json
{
  "goodsId": 0,
  "isApproved": true
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» goodsId|body|integer| 是 |none|
|» isApproved|body|boolean| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 商品批准展示

GET /api/goods/approve

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "price": 0,
    "pic": [
      "string"
    ],
    "description": "string",
    "goodsName": "string",
    "number": 0,
    "state": 0,
    "shopId": 0,
    "goodsId": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» price|number|true|none||none|
|» pic|[string]|true|none||none|
|» description|string|true|none||none|
|» goodsName|string|true|none||none|
|» number|integer|true|none||none|
|» state|integer|true|none||none|
|» shopId|integer|true|none||none|
|» goodsId|integer|true|none||none|

## GET 商品修改批准展示

GET /api/goods/change/approve

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "price": 0,
    "pic": [
      "string"
    ],
    "description": "string",
    "goodsName": "string",
    "number": 0,
    "state": 0,
    "presentPrice": 0,
    "presentPic": [
      "string"
    ],
    "presentDescription": "string",
    "presentGoodsName": "string",
    "presentNumber": 0,
    "msg": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» price|number|false|none||none|
|» pic|[string]|false|none||none|
|» description|string|false|none||none|
|» goodsName|string|false|none||none|
|» number|integer|false|none||none|
|» state|integer|false|none||none|
|» presentPrice|number|false|none||none|
|» presentPic|[string]|false|none||none|
|» presentDescription|string|false|none||none|
|» presentGoodsName|string|false|none||none|
|» presentNumber|integer|false|none||none|
|» msg|string|false|none||none|

## POST 批准商品修改

POST /api/goods/change/approve

> Body 请求参数

```json
{
  "goodsChangeId": 0,
  "isApproved": true
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» goodsChangeId|body|integer| 是 |none|
|» isApproved|body|boolean| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|记录不存在|Inline|

### 返回数据结构

## POST 批准商店删除

POST /api/shop/cancel/approve

> Body 请求参数

```json
{
  "shopCancelId": 0,
  "isApproved": true
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» shopCancelId|body|integer| 是 |none|
|» isApproved|body|boolean| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 商店删除批准展示

GET /api/shop/cancel/approve

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "shopCancelId": 0,
    "createAt": "string",
    "orderFinish": true,
    "shopId": 0,
    "state": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» shopCancelId|integer|true|none||none|
|» createAt|string|true|none||none|
|» orderFinish|boolean|true|none||none|
|» shopId|integer|true|none||none|
|» state|integer|true|none||none|

## POST 管理员账户充值

POST /api/admin/account/recharge

> Body 请求参数

```json
{
  "type": "string",
  "amount": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» type|body|string| 是 |应当为profit或者middle|
|» amount|body|number| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# e-shopping/展示

## GET 商店展示

GET /api/shop/display

展示已批准的商品

> 返回示例

> 200 Response

```json
[
  {
    "shopld": 0,
    "userId": 0,
    "state": 0,
    "shopName": "string",
    "tag": "string",
    "introduction": "string",
    "address": "string",
    "fund": 0,
    "registerDate": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» shopld|integer|false|none||none|
|» userId|integer|false|none||none|
|» state|integer|false|none||none|
|» shopName|string|false|none||none|
|» tag|string|false|none||none|
|» introduction|string|false|none||none|
|» address|string|false|none||none|
|» fund|number|false|none||none|
|» registerDate|string|false|none||none|

## GET 用户中心

GET /api/user/profile

查看个人信息

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "username": "string",
  "phone": "string",
  "email": "string",
  "role": "string",
  "idCard": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» username|string|true|none||none|
|» phone|string|true|none||none|
|» email|string|true|none||none|
|» role|string|true|none||none|
|» idCard|string|true|none||none|

## GET 商店账户展示

GET /api/shop/account

转账流水还在写

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "amount": 0,
  "registerFund": 0,
  "state": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» amount|number|true|none||none|
|» registerFund|number|true|none||none|
|» state|integer|true|none||0异常 1正常|

## GET 购物车展示

GET /api/cart

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "goodsName": "string",
    "price": 0,
    "number": 0,
    "pic": [
      "string"
    ],
    "shopName": "string",
    "valid": true,
    "remainNumber": 0,
    "goodsId": 0,
    "description": "string",
    "updateAt": "string",
    "userId": 0,
    "shopId": 0,
    "state": 0,
    "id": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» goodsName|string|false|none||none|
|» price|number|false|none||none|
|» number|integer|false|none||none|
|» pic|[string]|false|none||none|
|» shopName|string|false|none||none|
|» valid|boolean|true|none||none|
|» remainNumber|integer|false|none||可忽略|
|» goodsId|integer|false|none||可不显示|
|» description|string|false|none||none|
|» updateAt|string|false|none||none|
|» userId|integer|false|none||可忽略|
|» shopId|integer|false|none||可不显示|
|» state|integer|false|none||可忽略|
|» id|integer|false|none||none|

## GET 店铺中心

GET /api/shop/profile

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "shopName": "string",
  "tag": "string",
  "introduction": "string",
  "address": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» shopName|string|true|none||none|
|» tag|string|true|none||none|
|» introduction|string|true|none||none|
|» address|string|true|none||none|

## GET （全部）商品展示

GET /api/goods/display

只显示批准的

> 返回示例

> 200 Response

```json
[
  {
    "price": 0,
    "pic": [
      "string"
    ],
    "description": "string",
    "goodsName": "string",
    "number": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» price|number|false|none||none|
|» pic|[string]|false|none||none|
|» description|string|false|none||none|
|» goodsName|string|false|none||none|
|» number|integer|false|none||none|

## GET （单个）商品展示

GET /api/goods/{goodsId}

404

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|goodsId|path|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "price": 0,
  "pic": [
    "string"
  ],
  "description": "string",
  "goodsName": "string",
  "number": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» price|number|true|none||none|
|» pic|[string]|true|none||none|
|» description|string|true|none||none|
|» goodsName|string|true|none||none|
|» number|integer|true|none||none|

## GET 用户账户展示

GET /api/user/account

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "amount": 0,
  "state": true
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» amount|number|true|none||none|
|» state|boolean|true|none||0异常 1正常|

## GET 商品申请记录展示

GET /api/goods/apply/record

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "price": 0,
    "pic": [
      "string"
    ],
    "description": "string",
    "goodsName": "string",
    "number": 0,
    "state": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» price|number|true|none||none|
|» pic|[string]|true|none||none|
|» description|string|true|none||none|
|» goodsName|string|true|none||none|
|» number|integer|true|none||none|
|» state|integer|true|none||none|

## GET 商品修改记录展示

GET /api/goods/change/record

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "price": 0,
    "pic": "string",
    "description": "string",
    "goodsName": "string",
    "number": 0,
    "state": 0,
    "pastPrice": 0,
    "pastPic": "string",
    "pastDescription": "string",
    "pastGoodsName": "string",
    "pastNumber": 0,
    "msg": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» price|number|true|none||none|
|» pic|string|false|none||none|
|» description|string|false|none||none|
|» goodsName|string|false|none||none|
|» number|integer|false|none||none|
|» state|integer|true|none||none|
|» pastPrice|number|true|none||none|
|» pastPic|string|false|none||none|
|» pastDescription|string|true|none||none|
|» pastGoodsName|string|true|none||none|
|» pastNumber|integer|true|none||none|
|» msg|string|false|none||none|

## GET 管理员账户

GET /api/admin/account

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "profitAccountAmount": 0,
  "middleAccountAmount": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» profitAccountAmount|number|true|none||none|
|» middleAccountAmount|number|true|none||none|

# e-shopping/订单

## POST 购买商品

POST /api/goods/purchase

403

> Body 请求参数

```json
{
  "array": [
    {
      "goodsId": 0,
      "number": 0
    }
  ],
  "addressId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» array|body|[object]| 是 |none|
|»» goodsId|body|integer| 否 |none|
|»» number|body|integer| 否 |none|
|» addressId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{
  "group": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» group|integer|true|none||none|

## POST 确认收货

POST /api/order/receipt

404

> Body 请求参数

```json
{
  "orderId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 撤销订单

POST /api/order/cancel

> Body 请求参数

```json
{
  "group": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» group|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 获得一个订单

GET /api/order/{orderId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|orderId|path|integer| 是 |none|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "orderId": 0,
  "totalPrice": "string",
  "buyTime": "string",
  "address": "string",
  "consignee": "string",
  "consigneePhone": "string",
  "status": "string",
  "state": true,
  "goodsName": "string",
  "shopName": "string",
  "unitPrice": "string",
  "number": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» orderId|integer|true|none||none|
|» totalPrice|string|true|none||none|
|» buyTime|string|true|none||none|
|» address|string|true|none||none|
|» consignee|string|true|none||none|
|» consigneePhone|string|true|none||none|
|» status|string|true|none||none|
|» state|boolean|true|none||none|
|» goodsName|string|true|none||none|
|» shopName|string|true|none||none|
|» unitPrice|string|true|none||none|
|» number|string|true|none||none|

## POST 获得全部订单

POST /api/order/all/customer

> Body 请求参数

```json
{
  "state": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» state|body|string| 是 |all,unpaid,undelivered,unreceived,received,cancelled,refunded|

> 返回示例

> 200 Response

```json
[
  {
    "orderId": 0,
    "totalPrice": 0,
    "buyTime": "string",
    "address": "string",
    "consignee": "string",
    "consigneePhone": "string",
    "status": true,
    "state": "string",
    "goodsName": "string",
    "shopName": "string",
    "goodsUnitPrice": "string",
    "number": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» orderId|integer|false|none||none|
|» totalPrice|number|false|none||none|
|» buyTime|string|false|none||none|
|» address|string|false|none||none|
|» consignee|string|false|none||none|
|» consigneePhone|string|false|none||none|
|» status|boolean|false|none||none|
|» state|string|false|none||none|
|» goodsName|string|false|none||none|
|» shopName|string|false|none||none|
|» goodsUnitPrice|string|false|none||none|
|» number|integer|false|none||none|

## POST 支付订单

POST /api/goods/pay

> Body 请求参数

```json
{
  "group": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» group|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 删除订单

POST /api/order/delete

> Body 请求参数

```json
{
  "orderId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 退货退款

POST /api/order/refund

> Body 请求参数

```json
{
  "orderId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 展示待退款订单

GET /api/order/refund

> 返回示例

> 200 Response

```json
[
  {
    "orderId": 0,
    "order": [
      {
        "goodsId": 0,
        "number": 0
      }
    ]
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» orderId|integer|false|none||none|
|» order|[object]|false|none||none|
|»» goodsId|integer|false|none||none|
|»» number|integer|false|none||none|

## GET 同意退款查看

GET /api/order/refund/approve

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "orderId": 0,
    "totalPrice": 0,
    "buyTime": "string",
    "address": "string",
    "consignee": "string",
    "consigneePhone": "string",
    "status": true,
    "state": "string",
    "goodsName": "string",
    "shopName": "string",
    "goodsUnitPrice": "string",
    "number": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» orderId|integer|false|none||none|
|» totalPrice|number|false|none||none|
|» buyTime|string|false|none||none|
|» address|string|false|none||none|
|» consignee|string|false|none||none|
|» consigneePhone|string|false|none||none|
|» status|boolean|false|none||none|
|» state|string|false|none||none|
|» goodsName|string|false|none||none|
|» shopName|string|false|none||none|
|» goodsUnitPrice|string|false|none||none|
|» number|number|false|none||none|

## POST 同意退货退款

POST /api/order/refund/approve

> Body 请求参数

```json
{
  "orderId": 0,
  "isApproved": true
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |none|
|» isApproved|body|boolean| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 商家发货

POST /api/order/deliver

> Body 请求参数

```json
{
  "orderId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» orderId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 展示未发货订单

GET /api/order/deliver

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "orderId": 0,
    "order": [
      {
        "goodsId": 0,
        "number": 0
      }
    ]
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» orderId|integer|false|none||none|
|» order|[object]|false|none||none|
|»» goodsId|integer|false|none||none|
|»» number|number|false|none||none|

## GET 获得未支付订单

GET /api/order/unpaid

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 是 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "group": 0,
    "order": [
      {
        "orderId": 0,
        "totalPrice": 0,
        "buyTime": "string",
        "status": true,
        "state": "string",
        "goodsName": "string",
        "shopName": "string",
        "unitPrice": 0,
        "number": 0,
        "address": "string",
        "consignee": "string",
        "consigneePhone": "string"
      }
    ],
    "discounts": [
      {
        "amountArrive": 0,
        "amountMinus": 0,
        "times": 0
      }
    ]
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» group|integer|false|none||none|
|» order|[object]|false|none||none|
|»» orderId|integer|false|none||none|
|»» totalPrice|integer|false|none||none|
|»» buyTime|string|false|none||none|
|»» status|boolean|false|none||none|
|»» state|string|false|none||none|
|»» goodsName|string|false|none||none|
|»» shopName|string|false|none||none|
|»» unitPrice|integer|false|none||none|
|»» number|integer|false|none||none|
|»» address|string|false|none||none|
|»» consignee|string|false|none||none|
|»» consigneePhone|string|false|none||none|
|» discounts|[object]|false|none||none|
|»» amountArrive|integer|false|none||none|
|»» amountMinus|integer|false|none||none|
|»» times|integer|false|none||none|

## POST 获得全部订单商家

POST /api/order/all/seller

> Body 请求参数

```json
{
  "state": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» state|body|string| 是 |none|

> 返回示例

> 200 Response

```json
[
  {
    "orderId": 0,
    "totalPrice": 0,
    "buyTime": "string",
    "address": "string",
    "consignee": "string",
    "consigneePhone": "string",
    "status": true,
    "state": "string",
    "goodsName": "string",
    "shopName": "string",
    "goodsUnitPrice": "string",
    "number": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» orderId|integer|false|none||none|
|» totalPrice|integer|false|none||none|
|» buyTime|string|false|none||none|
|» address|string|false|none||none|
|» consignee|string|false|none||none|
|» consigneePhone|string|false|none||none|
|» status|boolean|false|none||none|
|» state|string|false|none||none|
|» goodsName|string|false|none||none|
|» shopName|string|false|none||none|
|» goodsUnitPrice|string|false|none||none|
|» number|integer|false|none||none|

# e-shopping/收货地址

## GET 查看收货地址

GET /api/delivery-address

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "id": 0,
    "address": "string",
    "consignee": "string",
    "consigneePhone": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» id|integer|false|none||none|
|» address|string|false|none||none|
|» consignee|string|false|none||none|
|» consigneePhone|string|false|none||none|

## POST 修改收货地址

POST /api/delivery-address/change

> Body 请求参数

```json
{
  "id": 0,
  "address": "string",
  "consignee": "string",
  "consigneePhone": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» id|body|integer| 是 |none|
|» address|body|string| 是 |新的|
|» consignee|body|string| 是 |新的|
|» consigneePhone|body|string| 是 |新的|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 新增收货地址

POST /api/delivery-address/apply

> Body 请求参数

```json
{
  "deliveryAddressId": 0,
  "address": "string",
  "consignee": "string",
  "consigneePhone": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» deliveryAddressId|body|integer| 是 |none|
|» address|body|string| 是 |none|
|» consignee|body|string| 是 |none|
|» consigneePhone|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 删除收货地址

POST /api/delivery-address/cancel

> Body 请求参数

```json
{
  "id": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» id|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# e-shopping/活动

## POST 开办活动

POST /api/activity/organize

> Body 请求参数

```json
{
  "startTime": "string",
  "endTime": "string",
  "name": "string",
  "tags": "string",
  "fund": 0,
  "amountArrive": 0,
  "amountMinus": 0,
  "registerFund": 0,
  "sellNumber": 0,
  "sellAmount": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» startTime|body|string| 是 |none|
|» endTime|body|string| 是 |none|
|» name|body|string| 是 |none|
|» tags|body|string| 是 |none|
|» fund|body|number| 是 |none|
|» amountArrive|body|number| 是 |none|
|» amountMinus|body|number| 是 |none|
|» registerFund|body|number| 是 |none|
|» sellNumber|body|integer| 是 |none|
|» sellAmount|body|number| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 申请活动

POST /api/activity/apply

> Body 请求参数

```json
{
  "activityId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» activityId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 活动批准展示

GET /api/activity/approve

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "startTime": "string",
  "lastTime": "string",
  "name": "string",
  "tags": [
    "string"
  ],
  "fund": 0,
  "amountArrive": 0,
  "amountMinus": 0,
  "registerFund": 0,
  "sellNumber": 0,
  "sellAmount": 0,
  "activityId": 0,
  "shopId": 0,
  "state": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» startTime|string|true|none||none|
|» lastTime|string|true|none||none|
|» name|string|true|none||none|
|» tags|[string]|true|none||none|
|» fund|number|true|none||none|
|» amountArrive|number|true|none||none|
|» amountMinus|number|true|none||none|
|» registerFund|number|true|none||none|
|» sellNumber|integer|true|none||none|
|» sellAmount|number|true|none||none|
|» activityId|integer|true|none||none|
|» shopId|integer|true|none||none|
|» state|integer|true|none||none|

## POST 活动批准

POST /api/activity/approve

> Body 请求参数

```json
{
  "activityId": 0,
  "shopId": 0,
  "isApproved": true
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» activityId|body|integer| 是 |none|
|» shopId|body|integer| 是 |none|
|» isApproved|body|boolean| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## GET 查活动

GET /api/activity

> 返回示例

> 200 Response

```json
{
  "startTime": "string",
  "lastTime": "string",
  "name": "string",
  "tags": [
    "string"
  ],
  "fund": 0,
  "amountArrive": 0,
  "amountMinus": 0,
  "registerFund": 0,
  "sellNumber": 0,
  "sellAmount": 0,
  "activityId": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» startTime|string|true|none||none|
|» lastTime|string|true|none||none|
|» name|string|true|none||none|
|» tags|[string]|true|none||none|
|» fund|number|true|none||none|
|» amountArrive|number|true|none||none|
|» amountMinus|number|true|none||none|
|» registerFund|number|true|none||可以不显示|
|» sellNumber|integer|true|none||none|
|» sellAmount|number|true|none||none|
|» activityId|integer|true|none||none|

## GET 根据活动查所有参与的商品

GET /api/activity/goods

> Body 请求参数

```json
{
  "activityId": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» activityId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
[
  {
    "price": 0,
    "pic": [
      "string"
    ],
    "description": "string",
    "goodsName": "string",
    "number": 0
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» price|integer|false|none||none|
|» pic|[string]|false|none||none|
|» description|string|false|none||none|
|» goodsName|string|false|none||none|
|» number|integer|false|none||none|

## GET 查正在进行的活动

GET /api/activity/active

> 返回示例

> 200 Response

```json
{
  "startTime": "string",
  "lastTime": "string",
  "name": "string",
  "tags": [
    "string"
  ],
  "fund": 0,
  "amountArrive": 0,
  "amountMinus": 0,
  "registerFund": 0,
  "sellNumber": 0,
  "sellAmount": 0,
  "activityId": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» startTime|string|true|none||none|
|» lastTime|string|true|none||none|
|» name|string|true|none||none|
|» tags|[string]|true|none||none|
|» fund|integer|true|none||none|
|» amountArrive|integer|true|none||none|
|» amountMinus|integer|true|none||none|
|» registerFund|integer|true|none||none|
|» sellNumber|integer|true|none||none|
|» sellAmount|integer|true|none||none|
|» activityId|integer|true|none||none|

# e-shopping/流水

## POST 用户查看流水

POST /api/account-record/user

> Body 请求参数

```json
{
  "days": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» days|body|integer| 是 |天数，无限制0|

> 返回示例

> 200 Response

```json
{
  "plus": [
    {
      "id": 0,
      "fromName": "string",
      "fromType": "string",
      "toName": "string",
      "toType": "string",
      "type": "string",
      "amount": 0,
      "createAt": "string"
    }
  ],
  "minus": [
    {
      "id": 0,
      "fromName": "string",
      "fromType": "string",
      "toName": "string",
      "toType": "string",
      "type": "string",
      "amount": 0,
      "createAt": "string"
    }
  ],
  "pureProfit": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» plus|[object]|true|none||none|
|»» id|integer|false|none||none|
|»» fromName|string|false|none||none|
|»» fromType|string|false|none||none|
|»» toName|string|false|none||none|
|»» toType|string|false|none||none|
|»» type|string|false|none||none|
|»» amount|integer|false|none||none|
|»» createAt|string|false|none||none|
|» minus|[object]|true|none||none|
|»» id|integer|false|none||none|
|»» fromName|string|false|none||none|
|»» fromType|string|false|none||none|
|»» toName|string|false|none||none|
|»» toType|string|false|none||none|
|»» type|string|false|none||none|
|»» amount|integer|false|none||none|
|»» createAt|string|false|none||none|
|» pureProfit|number|true|none||none|

## POST 商户查看流水

POST /api/account-record/shop

> Body 请求参数

```json
{
  "limit": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» limit|body|integer| 是 |天数，无限制0|

> 返回示例

> 200 Response

```json
[
  {
    "id": 0,
    "fromName": "string",
    "fromType": "string",
    "toName": "string",
    "toType": "string",
    "type": "string",
    "amount": 0,
    "createAt": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» id|integer|false|none||none|
|» fromName|string|false|none||none|
|» fromType|string|false|none||none|
|» toName|string|false|none||none|
|» toType|string|false|none||none|
|» type|string|false|none||none|
|» amount|integer|false|none||none|
|» createAt|string|false|none||none|

## POST 查看中间账户流水

POST /api/account-record/middle

> Body 请求参数

```json
{
  "limit": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» limit|body|integer| 是 |天数，无限制0|

> 返回示例

> 200 Response

```json
{
  "fromName": "string",
  "toName": "string",
  "type": "string",
  "amount": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» fromName|string|true|none||none|
|» toName|string|true|none||none|
|» type|string|true|none||none|
|» amount|number|true|none||none|

## POST 查看利润账户流水

POST /api/account-record/profit

> Body 请求参数

```json
{
  "limit": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|tokenHeader|header|string| 否 |none|
|body|body|object| 否 |none|
|» limit|body|integer| 是 |天数，无限制0|

> 返回示例

> 200 Response

```json
{
  "fromName": "string",
  "toName": "string",
  "type": "string",
  "amount": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» fromName|string|true|none||none|
|» toName|string|true|none||none|
|» type|string|true|none||none|
|» amount|number|true|none||none|

# 数据模型

