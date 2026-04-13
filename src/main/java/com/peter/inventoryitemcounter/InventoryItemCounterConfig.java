package com.peter.inventoryitemcounter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("inventoryitemcounter")
public interface InventoryItemCounterConfig extends Config
{
    @ConfigItem(
            keyName = "itemId",
            name = "Item ID",
            description = "The item ID to count in the inventory"
    )
    default int itemId()
    {
        return 995;
    }

    @ConfigItem(
            keyName = "showOverlay",
            name = "Show overlay",
            description = "Show the item count overlay"
    )
    default boolean showOverlay()
    {
        return true;
    }
}