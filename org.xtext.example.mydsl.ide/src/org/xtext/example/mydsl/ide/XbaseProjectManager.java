package org.xtext.example.mydsl.ide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.build.IncrementalBuilder.Result;
import org.eclipse.xtext.ide.server.ProjectManager;
import org.eclipse.xtext.resource.IExternalContentSupport.IExternalContentProvider;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ProjectDescription;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.workspace.IProjectConfig;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.inject.Provider;

public class XbaseProjectManager extends ProjectManager {
	
	private URLClassLoader classpathURIContext;

	@Override
	public void initialize(ProjectDescription description, IProjectConfig projectConfig,
			Procedure2<? super URI, ? super Iterable<Issue>> acceptor,
			IExternalContentProvider openedDocumentsContentProvider,
			Provider<Map<String, ResourceDescriptionsData>> indexProvider, CancelIndicator cancelIndicator) {
		super.initialize(description, projectConfig, acceptor, openedDocumentsContentProvider, indexProvider, cancelIndicator);
		List<URL> urls = new ArrayList<>();
		
//		URI rootPath = projectConfig.getPath();
//		if (rootPath != null) {
//			URI classpathURI = rootPath.appendSegment("classpath.json");
//			
//			try (FileInputStream in = new FileInputStream(classpathURI.toFileString())) {
//				JsonElement parse = new JsonParser().parse(new JsonReader(new InputStreamReader(in, Charsets.UTF_8)));
//				Iterator<JsonElement> iterator = parse.getAsJsonObject().getAsJsonArray("entries").iterator();
//				while (iterator.hasNext()) {
//					JsonElement next = iterator.next();
//					if (!(next instanceof JsonNull)) {
//						urls.add(new URL(next.getAsString()));
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		classpathURIContext = new URLClassLoader(urls.toArray(new URL[urls.size()]), null);
	}
	
	@Override
	protected XtextResourceSet createFreshResourceSet(ResourceDescriptionsData newIndex) {
		// TODO Auto-generated method stub
		XtextResourceSet resourceSet = super.createFreshResourceSet(newIndex);
		resourceSet.setClasspathURIContext(classpathURIContext);
		return resourceSet;
	}

}
