import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shop_app/provider/cart.dart';
import 'package:shop_app/provider/orders.dart';
import 'package:shop_app/screens/order_screen.dart';
import 'package:shop_app/widgets/cart_item.dart' as ci;

class CartScreen extends StatelessWidget {
  static const routeName = "/cart_screen";
  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<Cart>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text("Your Cart"),
      ),
      body: Column(
        children: [
          Card(
            margin: EdgeInsets.all(15),
            child: Padding(
              padding: EdgeInsets.all(8),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text("Total",
                      style:
                          TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
                  Spacer(),
                  Chip(
                    label: Text(
                      '\$${cart.getTotal.toStringAsFixed(2)}',
                      style: TextStyle(
                          color: Theme.of(context)
                              .primaryTextTheme
                              .headline6!
                              .color),
                    ),
                    backgroundColor: Theme.of(context).primaryColor,
                  ),
                  TextButton(
                      child: Text("ORDER NOW"),
                      onPressed: () {
                        if (cart.items.isNotEmpty) {
                          Provider.of<Orders>(context, listen: false).AddOrder(
                              cart.items.values.toList(), cart.getTotal);
                          cart.clearCart();
                        } else {
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              content: Text("PLease add items in Cart"),
                            ),
                          );
                        }
                      })
                ],
              ),
            ),
          ),
          SizedBox(
            height: 20,
          ),
          Expanded(
              child: ListView.builder(
            itemBuilder: (ctx, i) => ci.name(
                id: cart.items.values.toList()[i].id,
                productID: cart.items.keys.toList()[i],
                price: cart.items.values.toList()[i].price,
                quantity: cart.items.values.toList()[i].quantity,
                title: cart.items.values.toList()[i].title),
            itemCount: cart.itemCount,
          ))
        ],
      ),
    );
  }
}
