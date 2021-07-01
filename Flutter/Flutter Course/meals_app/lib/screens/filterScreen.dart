import 'package:flutter/material.dart';
import 'package:meals_app/screens/main_drawer.dart';

class FilterScreen extends StatefulWidget {
  static const routeName = '/filterscreen';
  final Function filter;
  final Map<String, bool> currentFilters;

  const FilterScreen(this.filter, this.currentFilters);

  @override
  _FilterScreenState createState() => _FilterScreenState();
}

class _FilterScreenState extends State<FilterScreen> {
  var _glutenfree = false;
  var _isvegan = false;
  var _vegetarian = false;
  var _islactosefree = false;

  @override
  void initState() {
    _glutenfree = widget.currentFilters['gluten'];
    _isvegan = widget.currentFilters['vegan'];
    _vegetarian = widget.currentFilters['vegetarian'];
    _islactosefree = widget.currentFilters['lactose'];
    super.initState();
  }

  Widget _buildSwitchListTitle(
      String title, String subtitle, bool currentValue, Function updateValue) {
    return SwitchListTile(
      value: currentValue,
      onChanged: updateValue,
      title: Text(title),
      subtitle: Text(subtitle),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Your FIlters'),
        actions: [
          IconButton(
              onPressed: () {
                final _SelectedList = {
                  'gluten': _glutenfree,
                  'vegan': _isvegan,
                  'vegetarian': _vegetarian,
                  'lactose': _islactosefree,
                };
                print(_SelectedList);
                widget.filter(_SelectedList);
              },
              icon: Icon(Icons.save)),
        ],
      ),
      drawer: main_drawer(),
      body: Column(
        children: [
          Container(
            padding: EdgeInsets.all(20),
            child: Text(
              'Adjust your meal selection',
              style: Theme.of(context).textTheme.headline6,
            ),
          ),
          Expanded(
              child: ListView(
            children: [
              _buildSwitchListTitle(
                  'Gluten-Free', 'Only include Gluten-free', _glutenfree,
                  (newvalue) {
                setState(
                  () {
                    _glutenfree = newvalue;
                  },
                );
              }),
              _buildSwitchListTitle(
                  'Lactose-Free', 'Only include Lactose-free', _islactosefree,
                  (newvalue) {
                setState(
                  () {
                    _islactosefree = newvalue;
                  },
                );
              }),
              _buildSwitchListTitle(
                  'Vegan-Free', 'Only include Vegan-free', _isvegan,
                  (newvalue) {
                setState(
                  () {
                    _isvegan = newvalue;
                  },
                );
              }),
              _buildSwitchListTitle('Vegetarian-Free',
                  'Only include Vegetarian-free', _vegetarian, (newvalue) {
                setState(
                  () {
                    _vegetarian = newvalue;
                  },
                );
              }),
            ],
          ))
        ],
      ),
    );
  }
}
