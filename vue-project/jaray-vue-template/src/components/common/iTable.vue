<template>
    <a-table 
        bordered
        size="small"
        :pagination="false"
        :data-source="tableData"
        :rowKey="row=>row.id"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        :customRow="onClickRow"
        :scroll="{ x: 1500, y: 600 }">
        <template v-for="(item, itemIndex) in tableTitle">
            <a-table-column v-if="!item.operation" 
                :fixed="item.fixed"
                :key="item.key" 
                :title="item.title"
                :sorter="item.sorter"
                filterMultiple
                :filters="item.filters"
                :data-index="item.dataIndex"
                :ellipsis="item.ellipsis"
                :width="item.width">
                <!-- formatter：自定义过滤器 -->
                <template slot-scope="text, record, index">
                    <template v-if="item.formatter">
                        <span v-if="item.renderComponent == 'tag'">
                            <!-- ** start ** 这一块做el-tag标签样式判断。不需要可以去除判断 -->
                            <a-tag size="mini">{{ item.formatter(null, null, record[item.key], null) }}</a-tag>
                            <!-- ** end ** -->
                        </span>
                        <span v-else>{{ item.formatter(null, null, record[item.key], null) }}</span>
                    </template>
                    <template v-else>{{ record[item.key] }}</template>
                </template>
            </a-table-column>
            <a-table-column v-if="item.operation" 
                :key="item.key" 
                :title="item.title" 
                :data-index="item.dataIndex">
                <template slot-scope="text, record">
                    <span v-for="(it, index) in item.operation">
                        <a @click.stop="it.clickFun(record)">{{it.name}}</a>
                        <a-divider v-if="index != (item.operation.length-1)" type="vertical" />
                    </span>
                </template>
            </a-table-column>
        </template>
    </a-table>
</template>

<script>

export default {
    name: 'iTable',
    props: {
        tableTitle: {
            type: Array,
            default: function(){
                return []
            }
        },
        tableData: {
            type: Array,
            default: function(){
                return []
            }
        },
        pagination: {

        },
        tableHeight: 0
    },
    data () {
        return {
            selectedRowKeys: [],
            currentPage: 1,
            // 表格高度自适应
            iKey: 0
        }
    },
    mounted: function(){
        
    },
    methods: {
        onSelectChange: function(selectedRowKeys) {
            let that = this
            that.selectedRowKeys = selectedRowKeys;
            that.transmitParent()
        },
        onClickRow(record, index) {
            let that = this
            return {
                on: {
                    click: () => {
                        that.$parent.rowClick(record)
                        //that.$emit('rowClick', record)
                    },
                },
            }
        },
        transmitParent(){
            let that = this
            that.$emit('transmitParent', that.selectedRowKeys)
        },
        filterHandler(value, row, column) {
            const property = column['property'];
            return row[property] === value;
        }
    },
    watch: {
        
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

</style>
