import 'package:budget_tracker/Widgets/transaction_list.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Budget Tracker',
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  // String titleInput;
  // String amountInput;
  final titleController = TextEditingController();
  final amountController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("BUDGET TRACKER"),
      ),
      body: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Container(
              width: double.infinity,
              child: Card(
                child: Text(
                  "HELLO",
                ),
                elevation: 10,
                color: Colors.blue,
              ),
            ),
            Card(
              elevation: 10,
              child: Container(
                padding: EdgeInsets.all(10),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    TextField(
                      decoration: InputDecoration(labelText: 'Title'),
                      //onChanged: (value) => titleInput = value,
                      controller: titleController,
                    ),
                    TextField(
                      decoration: InputDecoration(labelText: 'Amount'),
                      //onChanged: (value) => amountInput = value,
                      controller: amountController,
                      keyboardType: TextInputType.number,
                    ),
                    //TODO: Change FLat button to TextButton
                    FlatButton(
                      onPressed: () {
                        print(titleController.text);
                      },
                      child: Text(
                        "Add Transaction",
                      ),
                    ),
                  ],
                ),
              ),
            ),
            TransactionList()
          ]),
    );
  }
}
