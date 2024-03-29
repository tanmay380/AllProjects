import 'package:flutter/foundation.dart';

class CartItem {
  final String id;
  final String title;
  final int quantity;
  final double price;

  CartItem(
      {required this.id,
      required this.price,
      required this.quantity,
      required this.title});
}

class Cart with ChangeNotifier {
  Map<String, CartItem> _items = {};

  Map<String, CartItem> get items {
    return {..._items};
  }

  int get itemCount {
    return _items.length;
  }

  double get getTotal {
    var total = 0.0;
    _items.forEach((key, value) {
      total += value.price * value.quantity;
    });
    return total;
  }

  void addItems(String productid, double price, String title) {
    if (_items.containsKey(productid)) {
      // change quantity
      _items.update(
        productid,
        (existingItem) => CartItem(
          id: existingItem.id,
          title: existingItem.title,
          price: existingItem.price,
          quantity: existingItem.quantity + 1,
        ),
      );
    } else {
      //new Entry
      _items.putIfAbsent(
        productid,
        () => CartItem(
            id: DateTime.now().toString(),
            price: price,
            title: title,
            quantity: 1),
      );
    }
    notifyListeners();
  }

  void deleteItem(String id) {
    _items.forEach((key, value) {
      print(key +
          " : " +
          value.id +
          " : " +
          value.title +
          " : " +
          value.price.toString() +
          " : " +
          value.quantity.toString());
    });
    _items.remove(id);
    notifyListeners();
  }

  void removeSingleItem(String productid) {
    if (!_items.containsKey(productid)) return;

    if (_items[productid]!.quantity > 1) {
      _items.update(
          productid,
          (value) => CartItem(
              id: value.id,
              price: value.price,
              quantity: value.quantity - 1,
              title: value.title));
    } else {
      _items.remove(productid);
    }
    notifyListeners();
  }

  void clearCart() {
    _items = {};

    notifyListeners();
    _items.forEach((key, value) {
      print(key +
          " : " +
          value.id +
          " : " +
          value.title +
          " : " +
          value.price.toString() +
          " : " +
          value.quantity.toString());
    });
  }
}
