import 'package:budget_tracker/Widgets/chart_bar.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import 'package:budget_tracker/models/transaction.dart';

class Chart extends StatelessWidget {
  // const Chart({Key? key}) : super(key: key);
  final List<Transaction> recentTransaction;

  Chart(this.recentTransaction);

  List<Map<String, Object>> get groupedTransaction {
    return List.generate(7, (index) {
      final today = DateTime.now();
      final weekdaynumber = today.weekday;
      final weekDay = today.subtract(
        Duration(days: weekdaynumber - index),
      );
      print(
          '\t value of $index \t value of ${weekdaynumber - index} \t value of weekday  is ${DateFormat.E().format(weekDay).substring(0, 1)} ');
      var totalSum = 0.0;
      for (var i = 0; i < recentTransaction.length; i++) {
        if (recentTransaction[i].date.day == weekDay.day &&
            recentTransaction[i].date.month == weekDay.month &&
            recentTransaction[i].date.year == weekDay.year) {
          totalSum += recentTransaction[i].amount;
        }
      }
      //print('${DateFormat.E().format(weekDay)}  \' sum is \'  $totalSum');

      return {
        'day': DateFormat.E().format(weekDay).substring(0, 1).toUpperCase(),
        'amount': totalSum
      };
    }).toList();
  }

  double get maxSpending {
    return groupedTransaction.fold(
      0.0,
      (sum, item) {
        return sum + item['amount'];
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 6,
      margin: EdgeInsets.all(20),
      child: Padding(
        padding: const EdgeInsets.all(10.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: groupedTransaction.map(
            (data) {
              return Flexible(
                fit: FlexFit.tight,
                child: ChartBar(
                    data['day'],
                    data['amount'],
                    maxSpending == 0
                        ? 0.0
                        : (data['amount'] as double) / maxSpending),
              );
            },
          ).toList(),
        ),
      ),
    );
  }
}
