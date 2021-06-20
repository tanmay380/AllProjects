import 'package:flutter/material.dart';

class ChartBar extends StatelessWidget {
  final String label;
  final double spendingAmount;
  final double spendingPCTToal;

  const ChartBar(this.label, this.spendingAmount, this.spendingPCTToal);

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (ctx, constaints) {
        return Column(
          children: [
            Container(
              height: constaints.maxHeight * 0.15,
              child: FittedBox(
                child: Text('₹${spendingAmount.toStringAsFixed(0)}'),
              ),
            ),
            SizedBox(
              height: constaints.maxHeight * 0.05,
            ),
            Container(
              height: constaints.maxHeight * 0.6,
              width: 10,
              child: Stack(
                alignment: Alignment.bottomCenter,
                children: [
                  Container(
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.grey, width: 1.0),
                      color: Color.fromRGBO(220, 220, 220, 1),
                      borderRadius: BorderRadius.circular(20),
                    ),
                  ),
                  FractionallySizedBox(
                    heightFactor: spendingPCTToal,
                    child: Container(
                      decoration: BoxDecoration(
                          color: Theme.of(context).primaryColor,
                          borderRadius: BorderRadius.circular(10)),
                    ),
                  )
                ],
              ),
            ),
            SizedBox(
              height: constaints.maxHeight * 0.05,
            ),
            Container(
              height: constaints.maxHeight * 0.15,
              child: FittedBox(
                child: Text(label),
              ),
            ),
          ],
        );
      },
    );
  }
}
