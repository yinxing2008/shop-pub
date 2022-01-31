import 'package:flutter/material.dart';

lblSpacer(double padding) {
  return Container(
    margin: EdgeInsets.only(top: padding),
  );
}

lblVerticalSpacer(double padding) {
  return Container(
    margin: EdgeInsets.only(left: padding),
  );
}

Divider lblDivider() {
  return const Divider(
    height: 10,
    thickness: 1,
  );
}

emptyContainer() {
  return Container();
}
