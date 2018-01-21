/*******************************************************************************
 * Copyright (c) 2016 itemis AG (http://www.itemis.de) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.xtext.example.mydsl.ide

import com.google.inject.Guice
import com.google.inject.Inject
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.io.PrintWriter
import java.sql.Timestamp
import org.eclipse.lsp4j.jsonrpc.Launcher
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.xtext.ide.server.LanguageServerImpl
import org.eclipse.xtext.ide.server.ServerModule

/**
 * @author Sven Efftinge - Initial contribution and API
 * @since 2.11
 */
class ServerLauncher extends org.eclipse.xtext.ide.server.ServerLauncher {
	
	def static void main(String[] args) {
		launch(ServerLauncher.name, args, new ServerModule, new CustomServerModule)
	}

	

}
