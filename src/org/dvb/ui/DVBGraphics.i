package org.dvb.ui;

import java.awt.*;
import java.awt.image.*;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.util.Map;

public abstract class DVBGraphics extends Graphics2D
{
    static class DVBGraphicsImpl extends DVBGraphics
    {
        private Graphics2D real;
        public DVBGraphicsImpl(Graphics2D real)
        {
            this.real = real;
        }
#include "../../../../helpers.i"
        M0(Composite, getComposite)
        M0(GraphicsConfiguration, getDeviceConfiguration)
        M1V(setComposite, Composite)
        M0(FontRenderContext, getFontRenderContext)
        M1V(clip, Shape)
        M0(Stroke, getStroke)
        M0(Paint, getPaint)
        M0(Color, getBackground)
        M0(AffineTransform, getTransform)
        M0(RenderingHints, getRenderingHints)
        M1(Object, getRenderingHint, RenderingHints.Key)
        M2V(setRenderingHint, RenderingHints.Key, Object)
        M1V(addRenderingHints, Map)
        M1V(setRenderingHints, Map)
        M1V(setBackground, Color)
        M1V(setStroke, Stroke)
        M1V(setPaint, Paint)
        M1V(setTransform, AffineTransform)
        M1V(transform, AffineTransform)
        M2V(shear, double, double)
        M2V(scale, double, double)
        M3V(rotate, double, double, double)
        M1V(rotate, double)
        M2V(translate, double, double)
        M3(boolean, hit, Rectangle, Shape, boolean)
        M1V(fill, Shape)
        M1V(draw, Shape)
        M3V(drawGlyphVector, GlyphVector, float, float)
        M3V(drawString, AttributedCharacterIterator, float, float)
        M3V(drawString, String, float, float)
        M2V(drawRenderableImage, RenderableImage, AffineTransform)
        M2V(drawRenderedImage, RenderedImage, AffineTransform)
        M4V(drawImage, BufferedImage, BufferedImageOp, int, int)
        M3(boolean, drawImage, Image, AffineTransform, ImageObserver)
        M11(boolean, drawImage, Image, int, int, int, int, int, int, int, int, Color, ImageObserver)
        M10(boolean, drawImage, Image, int, int, int, int, int, int, int, int, ImageObserver)
        M7(boolean, drawImage, Image, int, int, int, int, Color, ImageObserver)
        M6(boolean, drawImage, Image, int, int, int, int, ImageObserver)
        M5(boolean, drawImage, Image, int, int, Color, ImageObserver)
        M4(boolean, drawImage, Image, int, int, ImageObserver)
        M0(Color, getColor)
        M0(Font, getFont)
        M1(FontMetrics, getFontMetrics, Font)
        M0(Graphics, create)
        M0(Rectangle, getClipBounds)
        M0(Shape, getClip)
        M4V(clearRect, int, int, int, int)
        M4V(clipRect, int, int, int, int)
        M6V(copyArea, int, int, int, int, int, int)
        M0V(dispose)
        M6V(drawArc, int, int, int, int, int, int)
        M4V(drawLine, int, int, int, int)
        M4V(drawOval, int, int, int, int)
        M3V(drawPolygon, int[], int[], int)
        M3V(drawPolyline, int[], int[], int)
        M6V(drawRoundRect, int, int, int, int, int, int)
        M3V(drawString, AttributedCharacterIterator, int, int)
        M3V(drawString, String, int, int)
        M6V(fillArc, int, int, int, int, int, int)
        M4V(fillOval, int, int, int, int)
        M3V(fillPolygon, int[], int[], int)
        M4V(fillRect, int, int, int, int)
        M6V(fillRoundRect, int, int, int, int, int, int)
        M4V(setClip, int, int, int, int)
        M1V(setClip, Shape)
        M1V(setColor, Color)
        M1V(setFont, Font)
        M0V(setPaintMode)
        M1V(setXORMode, Color)
        M2V(translate, int, int)
    }
}
