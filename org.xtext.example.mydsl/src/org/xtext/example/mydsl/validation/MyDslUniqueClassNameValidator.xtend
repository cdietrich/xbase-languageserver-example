package org.xtext.example.mydsl.validation

import com.google.inject.Inject
import java.io.File
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtext.IGrammarAccess
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.generator.trace.TraceFileNameProvider
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.validation.EValidatorRegistrar
import org.eclipse.xtext.xbase.validation.UniqueClassNameValidator
import org.xtext.example.mydsl.myDsl.MyDslPackage

class MyDslUniqueClassNameValidator extends UniqueClassNameValidator {
	
	@Inject
	TraceFileNameProvider traceFileNameProvider
	
	@Inject
	override protected register(EValidatorRegistrar registrar, IGrammarAccess grammarAccess) {
		touchEPackages()
		val entryRule = grammarAccess.grammar.rules.head
		if (entryRule instanceof ParserRule) {
			registrar.register(entryRule.type.classifier.getEPackage, this)
		}
	}
	
	def private touchEPackages() {
		MyDslPackage.eINSTANCE.name
		EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/xtext/xbase/Xbase")
		EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/xtext/common/JavaVMTypes")
		EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/xtext/xbase/Xtype")
	}
	
	override protected checkUniqueInIndex(JvmDeclaredType type, Iterable<IEObjectDescription> descriptions) {
		super.checkUniqueInIndex(type, descriptions.filter[
			if ("java".equals(EObjectURI.fileExtension)) {
				val trace = traceFileNameProvider.getTraceFromJava(EObjectURI.trimFragment.toFileString)
				trace === null || !new File(trace).exists
			} else {
				true
			}
			
		])
	}
	
}