package org.xtext.example.mydsl.ide;

import org.eclipse.xtext.ide.server.IWorkspaceConfigFactory;
import org.eclipse.xtext.ide.server.MultiProjectWorkspaceConfigFactory;
import org.eclipse.xtext.ide.server.ProjectManager;
import org.eclipse.xtext.util.IFileSystemScanner;

import com.google.inject.AbstractModule;

public final class CustomServerModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ProjectManager.class).to(XbaseProjectManager.class);
		bind(IFileSystemScanner.class).to(XbaseJavaIoFileSystemScanner.class);
	}
}