package com.peter.inventoryitemcounter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class InventoryItemCounterOverlay extends OverlayPanel
{
    private final InventoryItemCounterPlugin plugin;
    private final InventoryItemCounterConfig config;

    @Inject
    public InventoryItemCounterOverlay(
            InventoryItemCounterPlugin plugin,
            InventoryItemCounterConfig config)
    {
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.TOP_LEFT);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.showOverlay())
        {
            return null;
        }

        panelComponent.getChildren().clear();

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Item ID:")
                .right(String.valueOf(config.itemId()))
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Count:")
                .right(String.valueOf(plugin.getItemCount()))
                .rightColor(Color.GREEN)
                .build());

        return super.render(graphics);
    }
}