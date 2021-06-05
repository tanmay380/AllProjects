import 'package:budger_tracker/budget_repository.dart';
import 'package:budger_tracker/failure.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';

import 'item_model.dart';

void main() async {
  await dotenv.load(fileName: '.env');
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Budget Tracker',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primaryColor: Colors.white,
      ),
      home: BudgetScreen(),
    );
  }
}

class BudgetScreen extends StatefulWidget {
  @override
  _BudgetScreenState createState() => _BudgetScreenState();
}

class _BudgetScreenState extends State<BudgetScreen> {
  late Future<List<Item>> _future;

  @override
  void initState() {
    super.initState();
    _future = BudgetRepository().getItems();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Budget Tracker'),
      ),
      body: FutureBuilder<List<Item>>(
        future: _future,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            //show pie chart and list view
            final items = snapshot.data!;
            return ListView.builder(
              itemCount: items.length,
              itemBuilder: (BuildContext context, int index) {
                final item = items[index];
                return Container(
                  margin: const EdgeInsets.all(8.0),
                  decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(10.0),
                      border: Border.all(
                        width: 2.0,
                        color: getCategoryColor(item.category),
                      ),
                      boxShadow: const [
                        BoxShadow(
                          color: Colors.black26,
                          offset: Offset(0, 2),
                          blurRadius: 6.0,
                        )
                      ]),
                );
              },
            );
          } else if (snapshot.hasError) {
            print(snapshot.error);
            print(snapshot.data);
            final failure = snapshot.error as Failure;
            return Center(child: Text(failure.message));
          }
          return const Center(
            child: CircularProgressIndicator(),
          );
        },
      ),
    );
  }

  Color getCategoryColor(String category) {
    switch (category) {
      case 'Entertainment':
        return Colors.red[400]!;
      case 'Food':
        return Colors.green[400]!;
      case 'Personal':
        return Colors.blue[400]!;
      case 'Transportation':
        return Colors.purple[400]!;
      default:
        return Colors.orange[400]!;
    }
  }
}
