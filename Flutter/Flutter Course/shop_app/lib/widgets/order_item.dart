import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'dart:math';

import 'package:shop_app/provider/orders.dart' as ord;

class OrderItem extends StatefulWidget {
  final ord.OrderItem orderItem;

  OrderItem(this.orderItem);

  @override
  _OrderItemState createState() => _OrderItemState();
}

class _OrderItemState extends State<OrderItem> {
  var expanded = false;

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.all(10),
      child: Column(children: [
        ListTile(
          title: Text("\$${widget.orderItem.amount}"),
          subtitle: Text(
              DateFormat('DD-MM-YYYY hh:mm').format(widget.orderItem.dateTime)),
          trailing: IconButton(
              icon: Icon(!expanded ? Icons.expand_more : Icons.expand_less),
              onPressed: () {
                setState(() {
                  expanded = !expanded;
                });
              }),
        ),
        if (expanded)
          Container(
              padding: EdgeInsets.symmetric(horizontal: 15, vertical: 4),
              height: min(widget.orderItem.products.length * 20 + 20, 180),
              child: ListView(
                children: widget.orderItem.products
                    .map((e) => Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(
                              e.title,
                              style: TextStyle(
                                  fontSize: 18, fontWeight: FontWeight.bold),
                            ),
                            Text('${e.quantity}x \$${e.price}')
                          ],
                        ))
                    .toList(),
              )),
      ]),
    );
  }
}
