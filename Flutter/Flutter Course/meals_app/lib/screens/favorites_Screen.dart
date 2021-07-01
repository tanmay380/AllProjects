import 'package:flutter/material.dart';
import 'package:meals_app/items/meal_item.dart';
import 'package:meals_app/models/meals.dart';

class FavoritesScreen extends StatelessWidget {
  final List<Meal> favorite;

  FavoritesScreen(this.favorite);

  @override
  Widget build(BuildContext context) {
    if (favorite.isEmpty) {
      return Center(
        child: Text('You have no Favorites Yet, Add Some'),
      );
    } else {
      return ListView.builder(
        reverse: true,
        itemBuilder: (ctx, index) {
          return MealItem(
            id: favorite[index].id,
            title: favorite[index].title,
            affordability: favorite[index].affordability,
            duration: favorite[index].duration,
            complexity: favorite[index].complexity,
            imageUrl: favorite[index].imageUrl,
          );
        },
        itemCount: favorite.length,
      );
    }
  }
}
