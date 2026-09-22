package dev.dov.metalj.metal4.encoders;

import dev.dov.metalj.metal4.MTL4ArgumentTable;
import dev.dov.metalj.metal4.pipelines.MTL4MachineLearningPipelineState;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.heaps.MTLHeap;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4MachineLearningCommandEncoder extends MTL4CommandEncoder {
    private static final long DISPATCH_NETWORK_WITH_INTERMEDIATES_HEAP = ObjC.sel("dispatchNetworkWithIntermediatesHeap:");
    private static final long SET_ARGUMENT_TABLE = ObjC.sel("setArgumentTable:");
    private static final long SET_PIPELINE_STATE = ObjC.sel("setPipelineState:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4MachineLearningCommandEncoder(long id) {
        super(id);
    }

    public static MTL4MachineLearningCommandEncoder of(long id) {
        return new MTL4MachineLearningCommandEncoder(id);
    }

    @SneakyThrows
    public void setPipelineState(MTL4MachineLearningPipelineState state) {
        P.invokeExact(id, SET_PIPELINE_STATE, state.getId());
    }

    @SneakyThrows
    public void setArgumentTable(MTL4ArgumentTable table) {
        P.invokeExact(id, SET_ARGUMENT_TABLE, table.getId());
    }

    @SneakyThrows
    public void dispatchNetworkWithIntermediatesHeap(MTLHeap heap) {
        P.invokeExact(id, DISPATCH_NETWORK_WITH_INTERMEDIATES_HEAP, heap.getId());
    }
}
