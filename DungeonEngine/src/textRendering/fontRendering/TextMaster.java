package textRendering.fontRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import textRendering.fontMeshCreator.FontType;
import textRendering.fontMeshCreator.GUIText;
import textRendering.fontMeshCreator.TextMeshData;
import display.renderer.Loader;

public class TextMaster {
	
	private static Loader tloader;
	private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
	
	private static FontRenderer renderer;
	
	public static void init(Loader loader){
		renderer = new FontRenderer();
		tloader = loader;
	}
	
	public static void render(){
		renderer.render(texts);
	}
	
	public static void loadText(GUIText text){
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = tloader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if(textBatch == null){
			textBatch = new ArrayList<GUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public static void removeText(GUIText text){
		List<GUIText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if(textBatch.isEmpty()){
			texts.remove(text.getFont());
		}
	}
	
	public static Loader getLoader(){
		return tloader;
	}
	
	public static void cleanUp(){
		
	}
}
