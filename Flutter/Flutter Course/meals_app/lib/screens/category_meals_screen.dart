import 'package:flutter/material.dart';
import 'package:meals_app/items/meal_item.dart';
import '../dummy_data.dart';

class CategoryMealsScreen extends StatelessWidget {
  static const routeName = '\category-meals';

  // CategoryMealsScreen(this.id, this.title);

  // final String id;
  // final String title;

  @override
  Widget build(BuildContext context) {
    final routeArgs =
        ModalRoute.of(context)!.settings.arguments as Map<String, String>;
    final title = routeArgs['title'];
    final id = routeArgs['id'];
    final categoryMeals =
        DUMMY_MEALS.where((meal) => meal.categories.contains(id)).toList();
    return Scaffold(
        appBar: AppBar(
          title: Text(title!),
        ),
        body: ListView.builder(
          itemBuilder: (ctx, index) {
            return MealItem(
              title: categoryMeals[index].title,
              affordability: categoryMeals[index].affordability,
              duration: categoryMeals[index].duration,
              complexity: categoryMeals[index].complexity,
              imageUrl: categoryMeals[index].imageUrl,
            );
          },
          itemCount: categoryMeals.length,
        ));
  }
}
