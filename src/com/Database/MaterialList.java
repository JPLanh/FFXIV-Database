package com.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import android.content.Context;
import android.util.Log;

import com.Database.Materials;

public class MaterialList {
    private ArrayList<Materials> minerMaterials = new ArrayList<Materials>();
    private ArrayList<Materials> botanistMaterials = new ArrayList<Materials>();
    private List<String> areaList = new ArrayList<String>();
    private ArrayList<Reagents> reagentList = new ArrayList<Reagents>();

    //Debugging purposes
    private static ArrayList<String> map = new ArrayList<String>();
    private static ArrayList<String> zone = new ArrayList<String>();

    String readable;
    boolean searching = true;

    private Materials foundItem;
    private Reagents foundReagents;

    public MaterialList(){
    }
    public Materials materialSearch(String itemName){
        searching = true;
        for(int i = 0; i < minerMaterials.size(); i++){            
            if (minerMaterials.get(i).getName().toLowerCase().replace('_', ' ').equals(itemName)){
                foundItem = new Materials(minerMaterials.get(i));
                searching = false;
                break;
            }
        }
        if (searching){
            for(int i = 0; i < botanistMaterials.size(); i++){            
                if (botanistMaterials.get(i).getName().toLowerCase().equals(itemName)){
                    foundItem = new Materials(botanistMaterials.get(i));
                    searching = false;
                    break;
                }
            }
        }

        if (searching) {
            return null;
        }
        else {
            return foundItem;
        }
    }
    public Reagents reagentSearch(String itemName){
        searching = true;
        if (searching){
            for(int i = 0; i < reagentList.size(); i++){         
                if (reagentList.get(i).getName().toLowerCase().equals(itemName.toLowerCase())){

                    foundReagents = new Reagents(reagentList.get(i));
                    searching = false;
                    break;
                }
            }
        }

        if (searching) {
            return null;
        }
        else {
            return foundReagents;
        }
    }
    public void loadList(Context myContext) throws IOException{
        InputStream is;
        BufferedReader reader;
        String getLine;
        for (int count = 0; count < 2; count++){
            if (count == 0) readable = "Material/reagent.txt";
            else if (count == 1) readable = "Material/Bones.txt";
            is = myContext.getAssets().open(readable);
            reader = new BufferedReader(new InputStreamReader(is));
            Log.i("System.out", readable);

            getLine = reader.readLine();
            while (getLine != null){
                List<String> separator = new ArrayList<String>(Arrays.asList(getLine.split(" ")));
                Reagents reagent = new Reagents();
                reagent.setName(separator.get(0).replace('_', ' '));
                separator.remove(0);
                if (count == 1) Log.i("reading", reagent.getName());
                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Sold")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addSold(separator.get(1), separator.get(2), separator.get(3), separator.get(4));
                            for (int i = 0 ; i < 4; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }


                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Traded")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addTrade(separator.get(1), separator.get(2), separator.get(3), separator.get(4));
                            for (int i = 0 ; i < 4; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Quests")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addQuest(separator.get(1), separator.get(2));
                            for (int i = 0 ; i < 2; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }      
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Drops")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addMonster(separator.get(1), separator.get(2), separator.get(3));
                            for (int i = 0 ; i < 3; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Exploration")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addExploration(separator.get(1), separator.get(2));
                            for (int i = 0 ; i < 2; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Treasure")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addTreasure(separator.get(1), separator.get(2));
                            for (int i = 0 ; i < 2; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        if (separator.isEmpty()) break;
                        separator.remove(0);
                    }
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Duty")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addDuty(separator.get(1));
                            for (int i = 0 ; i < 1; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Fate")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addFate(separator.get(1), separator.get(2), separator.get(3));
                            for (int i = 0 ; i < 3; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("deSynthesis")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addDesynth(separator.get(1), separator.get(2));
                            for (int i = 0 ; i < 2; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }  
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Leves")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addLeve(separator.get(1), separator.get(3));
                            for (int i = 0 ; i < 2; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }
                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Airship")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addAirship(separator.get(1), separator.get(3));
                            for (int i = 0 ; i < 2; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }

                if (!separator.isEmpty()){
                    if (separator.get(0).equals("Gardening")){
                        separator.remove(1);
                        while (!separator.get(1).equals("/")){
                            reagent.addGardening(separator.get(1));
                            for (int i = 0 ; i < 1; i++){
                                separator.remove(1);
                            }
                        }
                        separator.remove(0);
                        separator.remove(0);
                    }
                }
                reagentList.add(reagent);
                getLine = reader.readLine();
            }
        }


        for (int i = 0; i < 2; i++){
            if (i == 0) readable = "Material/Botanist.txt";
            else if (i == 1) readable = "Material/Miner.txt";
            is = myContext.getAssets().open(readable);
            reader = new BufferedReader(new InputStreamReader(is));
            Log.i("System.out", readable);

            getLine = reader.readLine();
            while (getLine != null){
                List<String> separator = new ArrayList<String>(Arrays.asList(getLine.split(" ")));

                Materials material = new Materials();
                String mapHolder;
                int areaLvlHolder;
                String areaHolder;
                for (int j = 0; j < separator.size(); j++){
                    //Log.i("System.out", " " + separator.get(j));

                    if (i == 0) material.setClasses("BOT");
                    if (i == 1) material.setClasses("MIN");
                    if (j == 0) material.setName(separator.get(j).replace('_', ' '));
                    else if (j == 1) material.setLevel(Integer.parseInt((separator.get(j))));
                    else if (j == 2) material.setExtra(separator.get(j));
                    else if (j == 3) {
                        while (separator.get(j).equals("/") && j < separator.size() - 6 || j == 3){
                            j++;
                            material.addMap(separator.get(j));
                            mapHolder = separator.get(j);
                            material.addAreaLvl(Integer.parseInt(separator.get(j+1)));
                            areaLvlHolder = Integer.parseInt(separator.get(j+1));
                            material.addArea(separator.get(j+2));
                            areaHolder = separator.get(j+2);
                            material.addXCord(Integer.parseInt(separator.get(j+3)));
                            material.addYCord(Integer.parseInt(separator.get(j+4)));
                            j += 5;
                            if (j >= separator.size() - 1) break;
                            while (!separator.get(j).equals("/") && j < separator.size() - 2){
                                material.addMap(mapHolder);
                                material.addAreaLvl(areaLvlHolder);
                                material.addArea(areaHolder);
                                material.addXCord(Integer.parseInt(separator.get(j)));
                                material.addYCord(Integer.parseInt(separator.get(j+1)));
                                addZone(mapHolder, areaHolder);
                                j += 2;
                            }
                        }
                        if (j >= separator.size() - 6 && j > 4){
                            if (areaList.isEmpty()){
                                areaList.add(separator.get(4));
                            } else {
                                if (areaList.contains(separator.get(4))){
                                } else {
                                    areaList.add(separator.get(4));
                                }
                            }
                            if (i == 0) botanistMaterials.add(material);
                            else if (i == 1) minerMaterials.add(material);
                            getLine = reader.readLine();
                            break;
                        }
                    }
                }
            }
            Log.i("list of Maps", zone.toString());
            //test
            Log.i("list of Area", map.toString());
        }

    }

    public static void addZone(String mapAdd, String zoneAdd){
        boolean flag = false;
        if (zone.isEmpty()){
            map.add(mapAdd);
            zone.add(zoneAdd);
        } else {
            for (int i = 0; i < zone.size() ; i ++){
                if (zone.get(i).equals(zoneAdd)){
                    flag = true;
                }
            }
            if (!flag){
                map.add(mapAdd);
                zone.add(zoneAdd);
            }
        }
    }

}
