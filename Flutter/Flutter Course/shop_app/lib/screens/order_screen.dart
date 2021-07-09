import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'package:shop_app/provider/orders.dart' show Orders;
import 'package:shop_app/widgets/add_drawer.dart';

import 'package:shop_app/widgets/order_item.dart';

class OrderScreen extends StatelessWidget {
  static const routename = '/orderscreen';
  @override
  Widget build(BuildContext context) {
    final orderData = Provider.of<Orders>(context);
    return Scaffold(
      drawer: AppDrawer(),
      appBar: AppBar(
        title: Text("Your Orders"),
      ),
      body: ListView.builder(
        itemBuilder: (ctx, i) => OrderItem(orderData.order[i]),
        itemCount: orderData.order.length,
      ),
    );
  }
}
