package org.xtext.example.mydsl.ide;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.IFileSystemScanner.JavaIoFileSystemScanner;

public class XbaseJavaIoFileSystemScanner extends JavaIoFileSystemScanner {
	
	@Override
	public void scanRec(File file, IAcceptor<URI> acceptor) {
		if (file.getPath().contains("src-gen")) {
			return;
		}
		super.scanRec(file, acceptor);
	}

}
