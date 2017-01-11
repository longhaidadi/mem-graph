package ict.ada.gdb.query;

import ict.ada.gdb.model.Direction;
import ict.ada.gdb.model.GDBNode;
import ict.ada.gdb.util.TimeRange;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lon on 16-3-16.
 */
public class RelQuerySpec {
    private int nodeType;
    private String nodeId;
    private Set<Integer> requiredRelTypes = null;// null means all types are acceptable.
    //private Set<Integer> requiredNodeTypes = null; // null means all types are acceptable.
    private int tailType = GDBNode.ANY_TYPE;
    private String tailId = null;
    private TimeRange timeRange;
    private int minWeight = 0;
    private int maxWeight = Integer.MAX_VALUE;
    private int resultSize = 1000;
    private Direction dir = Direction.OUT;

    private RelQuerySpec() {

    }

    public int getNodeType() {
        return nodeType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public Direction getDir() {
        return dir;
    }

    /*public Set<Integer> getRequiredNodeTypes() {
        return requiredNodeTypes;
    }*/

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getResultSize() {
        return resultSize;
    }

    public int getTailType() {
        return tailType;
    }

    public String getTailId() {
        return tailId;
    }

    public boolean containsRelType(int type) {
        return requiredRelTypes == null || requiredRelTypes.contains(type);
    }

    public static class RelQuerySpecBuilder {
        private RelQuerySpec spec = new RelQuerySpec();

        /*when the head node is unknown**/
        public RelQuerySpecBuilder(){

        }
        public RelQuerySpecBuilder(RelQuerySpec spec){
            RelQuerySpecBuilder builder = new RelQuerySpecBuilder();
            builder.maxWeight(spec.maxWeight).minWeight(spec.minWeight)
                    .relType(spec.requiredRelTypes)
                    .resultSize(spec.resultSize).timeRange(spec.timeRange)
                    .tail(spec.tailType,spec.tailId);
            this.spec=builder.build();
        }
        public RelQuerySpecBuilder changeCenterNode(int nodeType,String nodeId){
            spec.nodeId=nodeId;
            spec.nodeType=nodeType;
            return this;
        }

        public RelQuerySpecBuilder(int nodeType, String nodeId) {
            spec.nodeType = nodeType;
            spec.nodeId = nodeId;
        }

        public RelQuerySpecBuilder setDir(Direction dir){
            spec.dir=dir;
            return this;
        }

        public RelQuerySpecBuilder relType(int relType) {
            if (spec.requiredRelTypes == null) {
                spec.requiredRelTypes = new HashSet<Integer>(16);
            }
            spec.requiredRelTypes.add(relType);
            return this;
        }
        public RelQuerySpecBuilder relType(Set<Integer> relTypes) {
            spec.requiredRelTypes=relTypes;
            return this;
        }


        /*public RelQuerySpecBuilder nodeType(int nodeType) {
            if (spec.requiredNodeTypes == null) {
                spec.requiredNodeTypes = new HashSet<Integer>(16);
            }
            spec.requiredNodeTypes.add(nodeType);
            return this;
        }

        public RelQuerySpecBuilder nodeType(Set<Integer> nodeTypes) {
            spec.requiredNodeTypes = nodeTypes;
            return this;
        }*/

        public RelQuerySpecBuilder tail(int type, String id) {
            spec.tailId = id;
            spec.tailType = type;
            return this;
        }

        public RelQuerySpecBuilder timeRange(TimeRange timeRange) {
            spec.timeRange = timeRange;
            return this;
        }

        public RelQuerySpecBuilder minWeight(int weight) {
            spec.minWeight = weight;
            return this;
        }

        public RelQuerySpecBuilder maxWeight(int weight) {
            spec.maxWeight = weight;
            return this;
        }

        public RelQuerySpecBuilder resultSize(int resultSize) {
            spec.resultSize = resultSize;
            return this;
        }

        public RelQuerySpec build() {
            return spec;
        }
    }
}
