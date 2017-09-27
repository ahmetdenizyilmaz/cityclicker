package com.ady.cityclicker.GameHelpers;


import com.ady.cityclicker.GameObjects.Building;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Created by ADY on 15.6.2017.
 */

public final class Constants {

    /*
        //WOOD TYPES
        public static final RecipeIngredients tree = new RecipeIngredients(1, 0, 0, 0, 0);
        public static final RecipeIngredients wood = tree.twice();
        public static final RecipeIngredients wood2 = wood.twice();
        public static final RecipeIngredients wood4 = wood2.twice();
        public static final RecipeIngredients plank = wood4.twice();
        public static final RecipeIngredients plank2 = plank.twice();
        public static final RecipeIngredients plank4 = plank2.twice();
        public static final RecipeIngredients lumbercamp1 = plank4.twice();
        public static final RecipeIngredients lumbercamp2 = lumbercamp1.twice();
        public static final RecipeIngredients lumbercamp3 = lumbercamp2.twice();
        public static final RecipeIngredients sawmill1 = lumbercamp3.twice();
        public static final RecipeIngredients sawmill2 = sawmill1.twice();
        public static final RecipeIngredients sawmill3 = sawmill2.twice();
        public static final RecipeIngredients woodfactory1 = sawmill3.twice();
        public static final RecipeIngredients woodfactory2 = woodfactory1.twice();
        public static final RecipeIngredients woodfactory3 = woodfactory2.twice();


        public static final ItemType helmet = new ItemType(0, 2, "Hard Hat", "şapka", new RecipeIngredients(1, 0, 0, 0, 0));
        public static final ItemType armor = new ItemType(1000, 3, "Targe", "şapka", new RecipeIngredients(2, 0, 0, 0, 0));
        public static final ItemType armor2 = new ItemType(1001, 5, "Buckler", "şapka", new RecipeIngredients(5, 0, 0, 0, 0, new IC(armor, 2, 2), new IC(helmet, 3, 2)));
        public static final ItemType chestarmor = new ItemType(2000, 10, "Cuirass", "şapka", new RecipeIngredients(10, 0, 0, 0, 0,new IC(armor2,2,2)));
        public static final ItemType chestarmor2 = new ItemType(2000, 10, "Scalemail", "şapka", new RecipeIngredients(30, 0, 0, 0, 0));
        public static final ItemType gloves = new ItemType(3000, 20, "Light Gauntlets", "şapka", new RecipeIngredients(80, 0, 0, 0, 0));
        public static final ItemType weapon = new ItemType(4000, 20, "Club", "şapka", new RecipeIngredients(100, 0, 0, 0, 0));*/
    public static OrderedMap<RecipeIngredients, Building> buildings = new OrderedMap<RecipeIngredients, Building>();
    public static Array<RecipeIngredients> keys = new Array<RecipeIngredients>();

    private Constants() {
    }
/*
    static {
        //Item(int production,int worth, String name, String info)
        buildings.put(tree, new Building(0, 0, "Tree", "Tree"));
        buildings.put(wood, new Building(0, 1, "Wood1", "Wood"));
        buildings.put(wood2, new Building(0, 2, "Wood2", "2Woods"));
        buildings.put(wood4, new Building(0, 4, "Wood4", "4Woods"));
        buildings.put(plank, new Building(0, 8, "Plank1", "Plank"));
        buildings.put(plank2, new Building(0, 16, "Plank2", "2Planks"));
        buildings.put(plank4, new Building(0, 32, "Plank4", "4Planks"));
        buildings.put(lumbercamp1, new Building(1, 0, "Lumbercamp1", "Lumbercamp1"));
        buildings.put(lumbercamp2, new Building(3, 0, "Lumbercamp2", "Lumbercamp2"));
        buildings.put(lumbercamp3, new Building(9, 0, "Lumbercamp3", "Lumbercamp3"));
        buildings.put(sawmill1, new Building(36, 0, "Sawmill1", "sawmill1"));
        buildings.put(sawmill2, new Building(108, 0, "Sawmill2", "sawmill2"));
        buildings.put(sawmill3, new Building(324, 0, "Sawmill3", "sawmill3"));
        buildings.put(woodfactory1, new Building(1296, 0, "Woodfactory1", "woodfactory1"));
        buildings.put(woodfactory2, new Building(5184, 0, "Woodfactory2", "woodfactory2"));
        buildings.put(woodfactory3, new Building(20736, 0, "Woodfactory3", "woodfactory3"));

        keys = buildings.keys().toArray(keys);

        System.out.println(buildings.containsKey(tree.add(tree)));
}*/
}