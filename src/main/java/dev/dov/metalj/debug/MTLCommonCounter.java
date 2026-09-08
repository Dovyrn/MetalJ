package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCommonCounter {
    public NSString named(String name) {
        return NSString.of(ObjC.symbol(name).reinterpret(8).get(ObjC.PTR, 0));
    }

    public NSString MTLCommonCounterTimestamp() {
        return named("MTLCommonCounterTimestamp");
    }

    public NSString MTLCommonCounterTessellationInputPatches() {
        return named("MTLCommonCounterTessellationInputPatches");
    }

    public NSString MTLCommonCounterVertexInvocations() {
        return named("MTLCommonCounterVertexInvocations");
    }

    public NSString MTLCommonCounterPostTessellationVertexInvocations() {
        return named("MTLCommonCounterPostTessellationVertexInvocations");
    }

    public NSString MTLCommonCounterClipperInvocations() {
        return named("MTLCommonCounterClipperInvocations");
    }

    public NSString MTLCommonCounterClipperPrimitivesOut() {
        return named("MTLCommonCounterClipperPrimitivesOut");
    }

    public NSString MTLCommonCounterFragmentInvocations() {
        return named("MTLCommonCounterFragmentInvocations");
    }

    public NSString MTLCommonCounterFragmentsPassed() {
        return named("MTLCommonCounterFragmentsPassed");
    }

    public NSString MTLCommonCounterComputeKernelInvocations() {
        return named("MTLCommonCounterComputeKernelInvocations");
    }

    public NSString MTLCommonCounterTotalCycles() {
        return named("MTLCommonCounterTotalCycles");
    }

    public NSString MTLCommonCounterVertexCycles() {
        return named("MTLCommonCounterVertexCycles");
    }

    public NSString MTLCommonCounterTessellationCycles() {
        return named("MTLCommonCounterTessellationCycles");
    }

    public NSString MTLCommonCounterPostTessellationVertexCycles() {
        return named("MTLCommonCounterPostTessellationVertexCycles");
    }

    public NSString MTLCommonCounterFragmentCycles() {
        return named("MTLCommonCounterFragmentCycles");
    }

    public NSString MTLCommonCounterRenderTargetCycles() {
        return named("MTLCommonCounterRenderTargetCycles");
    }

    public NSString MTLCommonCounterSetTimestamp() {
        return named("MTLCommonCounterSetTimestamp");
    }

    public NSString MTLCommonCounterSetStageUtilization() {
        return named("MTLCommonCounterSetStageUtilization");
    }

    public NSString MTLCommonCounterSetStatistic() {
        return named("MTLCommonCounterSetStatistic");
    }
}
