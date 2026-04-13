package com.peter.inventoryitemcounter;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
        name = "Inventory Item Counter",
        description = "Counts a selected item in your inventory",
        tags = {"inventory", "items", "counter"}
)
public class InventoryItemCounterPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private InventoryItemCounterConfig config;

    @Inject
    private InventoryItemCounterOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    @Getter
    private int itemCount = 0;

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
        updateItemCount();
        log.info("Inventory Item Counter started");
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
        itemCount = 0;
        log.info("Inventory Item Counter stopped");
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        updateItemCount();
    }

    private void updateItemCount()
    {
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);

        if (inventory == null)
        {
            itemCount = 0;
            return;
        }

        int targetItemId = config.itemId();
        int total = 0;

        for (Item item : inventory.getItems())
        {
            if (item != null && item.getId() == targetItemId)
            {
                total += item.getQuantity();
            }
        }

        itemCount = total;
    }

    @Provides
    InventoryItemCounterConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(InventoryItemCounterConfig.class);
    }
}