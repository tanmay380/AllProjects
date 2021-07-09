import 'package:flutter/material.dart';
import 'package:shop_app/provider/cart.dart';

class OrderItem {
  final String id;
  final double amount;
  final List<CartItem> products;
  final DateTime dateTime;

  OrderItem(
      {required this.id,
      required this.amount,
      required this.products,
      required this.dateTime});
}

class Orders with ChangeNotifier {
  List<OrderItem> _orders = [];

  List<OrderItem> get order {
    return [..._orders];
  }

  void AddOrder(List<CartItem> cartproducts, price) {
    _orders.insert(
        0,
        new OrderItem(
          id: DateTime.now().toString(),
          amount: price,
          products: cartproducts,
          dateTime: DateTime.now(),
        ));
    notifyListeners();
  }
}
