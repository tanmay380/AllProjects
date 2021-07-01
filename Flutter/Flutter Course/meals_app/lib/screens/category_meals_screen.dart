import 'package:flutter/material.dart';
import 'package:meals_app/items/meal_item.dart';
import 'package:meals_app/models/meals.dart';

class CategoryMeals extends StatefulWidget {
  static const routeName = '\category-meals';
  final List<Meal> availableMEals;

  const CategoryMeals(this.availableMEals);

  @override
  _CategoryMealsState createState() => _CategoryMealsState();
}

class _CategoryMealsState extends State<CategoryMeals> {
  String categoryTitle;
  List<Meal> displayedMeal;

  @override
  void initState() {
    super.initState();
  }

  @override
  void didChangeDependencies() {
    final routeArgs =
        ModalRoute.of(context).settings.arguments as Map<String, String>;
    categoryTitle = routeArgs['title'];
    final id = routeArgs['id'];
    displayedMeal = widget.availableMEals
        .where((meal) => meal.categories.contains(id))
        .toList();
    super.didChangeDependencies();
  }

  void RemoveMeal(String mealid) {
    setState(() {
      displayedMeal.removeWhere((element) => element.id == mealid);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(categoryTitle),
        ),
        body: ListView.builder(
          itemBuilder: (ctx, index) {
            return MealItem(
              id: displayedMeal[index].id,
              title: displayedMeal[index].title,
              affordability: displayedMeal[index].affordability,
              duration: displayedMeal[index].duration,
              complexity: displayedMeal[index].complexity,
              imageUrl: displayedMeal[index].imageUrl,
            );
          },
          itemCount: displayedMeal.length,
        ));
  }
}
