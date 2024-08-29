package com.lilyfena.NUBClient.ui;

import com.lilyfena.NUBClient.Mc;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;

public class BThackRenderUtils2 implements Mc {
    public static final VertexConsumerProvider.Immediate bufferSource = mc.getBufferBuilders().getEntityVertexConsumers();
    public static final MatrixStack pose = new MatrixStack();
    public static final DrawContext guiGraphics = new DrawContext(mc, bufferSource);


    public static void drawRect(int x1, int y1, int x2, int y2, int color) {

        Matrix4f matrix4f = pose.peek().getPositionMatrix();

        if (x1 < x2) {
            int i = x1;
            x1 = x2;
            x2 = i;
        }

        if (y1 < y2) {
            int j = y1;
            y1 = y2;
            y2 = j;
        }

        float f = (float) ColorHelper.Argb.getAlpha(color) / 255.0F;
        float g = (float) ColorHelper.Argb.getRed(color) / 255.0F;
        float h = (float) ColorHelper.Argb.getGreen(color) / 255.0F;
        float j = (float) ColorHelper.Argb.getBlue(color) / 255.0F;
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderLayer.getGui());
        //������-����
        vertexconsumer.vertex(matrix4f, (float)x1, (float)y1, (float)0).color(g, h, j, f).next();
        //�����-�����
        vertexconsumer.vertex(matrix4f, (float)x1, (float)y2, (float)0).color(g, h, j, f).next();
        //�����-�����
        vertexconsumer.vertex(matrix4f, (float)x2, (float)y2, (float)0).color(g, h, j, f).next();
        //�����-����
        vertexconsumer.vertex(matrix4f, (float)x2, (float)y1, (float)0).color(g, h, j, f).next();
        guiGraphics.draw();
    }

    public static void drawLine(float x1, float y1, float x2, float y2, float wight, int color) {

        Matrix4f matrix4f = pose.peek().getPositionMatrix();

        if (x1 > x2) {
            float i = x1;
            x1 = x2;
            x2 = i;
        }

        if (y1 > y2) {
            float j = y1;
            y1 = y2;
            y2 = j;
        }

        wight = wight / 2;

        float f = (float) ColorHelper.Argb.getAlpha(color) / 255.0F;
        float g = (float) ColorHelper.Argb.getRed(color) / 255.0F;
        float h = (float) ColorHelper.Argb.getGreen(color) / 255.0F;
        float j = (float) ColorHelper.Argb.getBlue(color) / 255.0F;
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderLayer.getGui());
        //������-����
        vertexconsumer.vertex(matrix4f, x2 + wight, y2 + wight, (float)0).color(g, h, j, f).next();
        //�����-�����
        vertexconsumer.vertex(matrix4f, x2 + wight, y2 - wight, (float)0).color(g, h, j, f).next();
        //�����-�����
        vertexconsumer.vertex(matrix4f, x1 - wight, y1 - wight, (float)0).color(g, h, j, f).next();
        //�����-����
        vertexconsumer.vertex(matrix4f, x1 - wight, y1 + wight, (float)0).color(g, h, j, f).next();
        guiGraphics.draw();
    }

    public static void drawVerticalGradientRect(int x1, int y1, int x2, int y2, int startColor, int endColor) {
        float f = (float) ColorHelper.Argb.getAlpha(startColor) / 255.0F;
        float f1 = (float) ColorHelper.Argb.getRed(startColor) / 255.0F;
        float f2 = (float)ColorHelper.Argb.getGreen(startColor) / 255.0F;
        float f3 = (float) ColorHelper.Argb.getBlue(startColor) / 255.0F;
        float f4 = (float) ColorHelper.Argb.getAlpha(endColor) / 255.0F;
        float f5 = (float) ColorHelper.Argb.getRed(endColor) / 255.0F;
        float f6 = (float) ColorHelper.Argb.getGreen(endColor) / 255.0F;
        float f7 = (float) ColorHelper.Argb.getBlue(endColor) / 255.0F;
        Matrix4f matrix4f = pose.peek().getPositionMatrix();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderLayer.getGui());
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y1, (float)0).color(f1, f2, f3, f).next();
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y2, (float)0).color(f5, f6, f7, f4).next();
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y2, (float)0).color(f5, f6, f7, f4).next();
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y1, (float)0).color(f1, f2, f3, f).next();
        guiGraphics.draw();
    }

    public static void drawHorizontalGradientRect(int x1, int y1, int x2, int y2, int startColor, int endColor) {
        float startAlpha = (float) ColorHelper.Argb.getAlpha(startColor) / 255.0F;
        float startRed = (float) ColorHelper.Argb.getRed(startColor) / 255.0F;
        float startGreen = (float) ColorHelper.Argb.getGreen(startColor) / 255.0F;
        float startBlue = (float) ColorHelper.Argb.getBlue(startColor) / 255.0F;

        float endAlpha = (float) ColorHelper.Argb.getAlpha(endColor) / 255.0F;
        float endRed = (float) ColorHelper.Argb.getRed(endColor) / 255.0F;
        float endGreen = (float) ColorHelper.Argb.getGreen(endColor) / 255.0F;
        float endBlue = (float) ColorHelper.Argb.getBlue(endColor) / 255.0F;
        Matrix4f matrix4f = pose.peek().getPositionMatrix();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderLayer.getGui());
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y1, (float)0).color(startRed, startGreen, startBlue, startAlpha).next();
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y2, (float)0).color(startRed, startGreen, startBlue, startAlpha).next();
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y2, (float)0).color(endRed, endGreen, endBlue, endAlpha).next();
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y1, (float)0).color(endRed, endGreen, endBlue, endAlpha).next();
        guiGraphics.draw();
    }

    public static void draw4ColorRect(int x1, int y1, int x2, int y2, int x1y1Color, int x2y1Color, int x1y2Color, int x2y2Color) {
        float x1y1Alpha = (float) ColorHelper.Argb.getAlpha(x1y1Color) / 255.0F;
        float x1y1Red = (float) ColorHelper.Argb.getRed(x1y1Color) / 255.0F;
        float x1y1Green = (float) ColorHelper.Argb.getGreen(x1y1Color) / 255.0F;
        float x1y1Blue = (float) ColorHelper.Argb.getBlue(x1y1Color) / 255.0F;

        float x2y1Alpha = (float) ColorHelper.Argb.getAlpha(x2y1Color) / 255.0F;
        float x2y1Red = (float) ColorHelper.Argb.getRed(x2y1Color) / 255.0F;
        float x2y1Green = (float) ColorHelper.Argb.getGreen(x2y1Color) / 255.0F;
        float x2y1Blue = (float) ColorHelper.Argb.getBlue(x2y1Color) / 255.0F;

        float x1y2Alpha = (float) ColorHelper.Argb.getAlpha(x1y2Color) / 255.0F;
        float x1y2Red = (float) ColorHelper.Argb.getRed(x1y2Color) / 255.0F;
        float x1y2Green = (float) ColorHelper.Argb.getGreen(x1y2Color) / 255.0F;
        float x1y2Blue = (float) ColorHelper.Argb.getBlue(x1y2Color) / 255.0F;

        float x2y2Alpha = (float) ColorHelper.Argb.getAlpha(x2y2Color) / 255.0F;
        float x2y2Red = (float) ColorHelper.Argb.getRed(x2y2Color) / 255.0F;
        float x2y2Green = (float) ColorHelper.Argb.getGreen(x2y2Color) / 255.0F;
        float x2y2Blue = (float) ColorHelper.Argb.getBlue(x2y2Color) / 255.0F;

        Matrix4f matrix4f = pose.peek().getPositionMatrix();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderLayer.getGui());
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y1, (float)0).color(x1y1Red, x1y1Green, x1y1Blue, x1y1Alpha).next();
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y2, (float)0).color(x1y2Red, x1y2Green, x1y2Blue, x1y2Alpha).next();
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y2, (float)0).color(x2y2Red, x2y2Green, x2y2Blue, x2y2Alpha).next();
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y1, (float)0).color(x2y1Red, x2y1Green, x2y1Blue, x2y1Alpha).next();
        guiGraphics.draw();
    }

    public static void drawOutlineRect(int x1, int y1, int x2, int y2, int depth, int colour) {
        int outlineX;
        int outlineY;
        outlineX = x1 > x2 ? -depth : depth;
        outlineY = y1 > y2 ? depth : -depth;

        drawRect(x1,y1, x1 + outlineX, y2, colour);
        drawRect(x1 + outlineX, y2, x2, y2 + outlineY, colour);
        drawRect(x2, y2 + outlineY, x2 - outlineX, y1, colour);
        drawRect(x2 - outlineX, y1, x1, y1 - outlineY, colour);
    }

    public static void drawSquare(int x1, int y1, int size, int color) {
        Matrix4f matrix4f = pose.peek().getPositionMatrix();

        float f3 = (float) ColorHelper.Argb.getAlpha(color) / 255.0F;
        float f = (float) ColorHelper.Argb.getRed(color) / 255.0F;
        float f1 = (float) ColorHelper.Argb.getGreen(color) / 255.0F;
        float f2 = (float) ColorHelper.Argb.getBlue(color) / 255.0F;
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderLayer.getGui());
        vertexconsumer.vertex(matrix4f, (float)x1 - size, (float)y1 - size, (float)0).color(f, f1, f2, f3).next();
        vertexconsumer.vertex(matrix4f, (float)x1 - size, (float)y1 + size, (float)0).color(f, f1, f2, f3).next();
        vertexconsumer.vertex(matrix4f, (float)x1 + size, (float)y1 + size, (float)0).color(f, f1, f2, f3).next();
        vertexconsumer.vertex(matrix4f, (float)x1 + size, (float)y1 - size, (float)0).color(f, f1, f2, f3).next();
        guiGraphics.draw();
    }

    public static void drawTriangle(float x, float y, float size, float theta, int color) {
        double radians = Math.toRadians(theta);

        float xA = -size;
        float yA = size;
        double newXA = xA * Math.cos(radians) + yA * Math.sin(radians);
        double newYA = yA * Math.cos(radians) - xA * Math.sin(radians);

        float xB = 0;
        float yB = -(size * 2);
        double newXB = xB * Math.cos(radians) + yB * Math.sin(radians);
        double newYB = yB * Math.cos(radians) - xB * Math.sin(radians);

        float xC = size;
        float yC = size;
        double newXC = xC * Math.cos(radians) + yC * Math.sin(radians);
        double newYC = yC * Math.cos(radians) - xC * Math.sin(radians);


        Matrix4f matrix4f = pose.peek().getPositionMatrix();

        float f3 = (float) ColorHelper.Argb.getAlpha(color) / 255.0F;
        float f = (float) ColorHelper.Argb.getRed(color) / 255.0F;
        float f1 = (float) ColorHelper.Argb.getGreen(color) / 255.0F;
        float f2 = (float) ColorHelper.Argb.getBlue(color) / 255.0F;
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderLayer.getGui());
        vertexconsumer.vertex(matrix4f, (float)(x + newXB), (float)(y + newYB), (float)0).color(f, f1, f2, f3).next();
        vertexconsumer.vertex(matrix4f, (float)(x + newXA), (float)(y + newYA), (float)0).color(f, f1, f2, f3).next();
        vertexconsumer.vertex(matrix4f, (float)(x + newXC), (float)(y + newYC), (float)0).color(f, f1, f2, f3).next();
        vertexconsumer.vertex(matrix4f, (float)(x + newXB), (float)(y + newYB), (float)0).color(f, f1, f2, f3).next();
        guiGraphics.draw();


    }

    public static int drawString(String text, int x1, int y1, int color) {
        Text component = Text.of(text);

        return guiGraphics.drawText(mc.textRenderer, component, x1, y1, color, true);
    }

    public static int drawCenteredString(String text, int x1, int y1, int color) {
        return guiGraphics.drawText(mc.textRenderer, text, x1 - mc.textRenderer.getWidth(text) / 2, y1, color, true);
    }

    public static void drawScaledCustomSizeModalRect(Identifier texture , float x, float y, float u, float v, float uWidth, float vHeight, float width, float height, float tileWidth, float tileHeight) {
        float f = 1.0F / tileWidth;
        float f1 = 1.0F / tileHeight;

        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        Matrix4f matrix4f = pose.peek().getPositionMatrix();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferBuilder.vertex(matrix4f, x, y + height, 0.0f).texture(u * f, (v + vHeight) * f1).next();
        bufferBuilder.vertex(matrix4f, x + width, y + height, 0.0f).texture((u + uWidth) * f, (v + vHeight) * f1).next();
        bufferBuilder.vertex(matrix4f, x + width, y, 0.0f).texture((u + uWidth) * f, v * f1).next();
        bufferBuilder.vertex(matrix4f, x, y, 0.0f).texture(u * f, v * f1).next();
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
    }

    public static void drawItem(DrawContext context, ItemStack stack, int x, int y, String amountText) {
        context.getMatrices().push();
        context.getMatrices().translate(0.0f, 0.0f, 232.0f);
        context.drawItem(stack, x, y);
        context.drawItemInSlot(mc.textRenderer, stack, x, y, amountText);
        context.getMatrices().pop();
    }
}
