import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_order_list_resp_entity.g.dart';

@JsonSerializable()
class QueryOrderListResp {
  late int code;
  late dynamic msg;
  late List<QueryOrderListRespData> data;

  QueryOrderListResp();

  factory QueryOrderListResp.fromJson(Map<String, dynamic> json) => $QueryOrderListRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderListRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderListRespData {
  late String orderId;
  late int status;
  late int createTime;
  late List<QueryOrderListRespDataItem> list;
  late QueryOrderListRespDataUserAddr userAddr;

  QueryOrderListRespData();

  factory QueryOrderListRespData.fromJson(Map<String, dynamic> json) => $QueryOrderListRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderListRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderListRespDataItem {
  late dynamic orderId;
  late int status;
  late String goodsId;
  late String name;
  late int price;
  late String squarePic;
  late int quantity;

  QueryOrderListRespDataItem();

  factory QueryOrderListRespDataItem.fromJson(Map<String, dynamic> json) => $QueryOrderListRespDataListFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderListRespDataListToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderListRespDataUserAddr {
  late String id;
  late int userId;
  late String name;
  late String phone;
  late String region;
  late String address;
  late bool defaultAddress;
  late int addrType;

  QueryOrderListRespDataUserAddr();

  factory QueryOrderListRespDataUserAddr.fromJson(Map<String, dynamic> json) =>
      $QueryOrderListRespDataUserAddrFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderListRespDataUserAddrToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
