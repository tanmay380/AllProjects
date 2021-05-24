import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class HomePage extends StatefulWidget {
  HomePage({Key key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List currencies;

  var queryResponse = {
    'start':'1',
    'limit':'50',
    'convert':'USD'
  };

  @override
  void initState() async {
    super.initState();
    currencies = await getCurrencies();
  }

  Future<List> getCurrencies() async {
    String cryptoURL =
        "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    http.Response response = await http.get(
      Uri.parse(cryptoURL),      
      headers: {HttpHeaders.acceptHeader: "application/json",
                HttpHeaders.authorizationHeader: "9fabc234-ced0-40cc-886e-e314bbb9e47b"},
      
    );
    final responseJson= jsonDecode(response.body)
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("CRYPTO APP"),
      ),
      body: _cryptoWidget(),
    );
  }

  Widget _cryptoWidget() {
    return new Container(
        child: new Flexible(
      child: new ListView.builder(
        itemBuilder: (BuildContext context, int index) {},
        itemCount: 0,
      ),
    ));
  }
}
