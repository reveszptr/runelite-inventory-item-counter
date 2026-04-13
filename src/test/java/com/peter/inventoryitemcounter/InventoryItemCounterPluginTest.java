package com.peter.inventoryitemcounter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class InventoryItemCounterPluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(InventoryItemCounterPlugin.class);
        RuneLite.main(args);
    }
}