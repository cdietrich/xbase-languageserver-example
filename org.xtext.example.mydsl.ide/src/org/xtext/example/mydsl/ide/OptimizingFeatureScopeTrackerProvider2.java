package org.xtext.example.mydsl.ide;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.typesystem.internal.FeatureScopeTracker;
import org.eclipse.xtext.xbase.typesystem.internal.IFeatureScopeTracker;
import org.eclipse.xtext.xbase.typesystem.internal.OptimizingFeatureScopeTrackerProvider;

public class OptimizingFeatureScopeTrackerProvider2 extends OptimizingFeatureScopeTrackerProvider {
	
	@Override
	public IFeatureScopeTracker track(EObject root) {
		return new FeatureScopeTracker2();
	}
	
	public static class FeatureScopeTracker2 extends FeatureScopeTracker {
		
	}

}
