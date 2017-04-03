package com.example.yy.test_listview;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListView extends AppCompatActivity {

    ListView lv;

    String[] userName={"xxx","lisi","wangwu","zhaoliu"}; //这里第一列所要显示的人名
    String[] userId={"1001","1002","1003","1004"};  //这里是人名对应的ID

    String[] from={"name","id"};              // 这里是ListView显示内容每一列的列名
    int[] to={R.id.user_name,R.id.user_id};   // item中控件的id

    private List<Map<String, Object>> data;
    MyAdapter adapter = null;

    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<4;i++)
        {
            map = new HashMap<String, Object>();
            map.put(from[0], userName[i]);
            map.put(from[1], userId[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);

        lv = (ListView)findViewById(R.id.abc_lv);

        data = getData();

        adapter = new MyAdapter(this);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String _name = (String)data.get(position).get("name");
                String _id = (String)data.get(position).get("id");

                String _text = "选中的数据项ID是：" + _id + "，名称是：" + _name;

                Toast toast=Toast.makeText(getApplicationContext(), _text, Toast.LENGTH_SHORT);
                toast.show();

                data.remove(position);// 删除数据后，需要刷新数据
                adapter.notifyDataSetChanged();
            }
        });

/*
        ArrayList<HashMap<String,String>> listData = null;
        listData = new ArrayList<HashMap<String,String>>();
        for(int i=0; i<4; i++){
            HashMap<String,String> map=null;
            map=new HashMap<String,String>();       //为避免产生空指针异常，有几列就创建几个map对象
            map.put(from[0], userName[i]);
            map.put(from[1], userId[i]);
            listData.add(map);
        }
        // 创建一个SimpleAdapter对象
        SimpleAdapter adapter = new SimpleAdapter(this, listData, R.layout.item, from, to);

        // 为ListView设置适配器
        lv.setAdapter(adapter);
*/
    }

    static class ViewHolder
    {
        public TextView name;
        public TextView id;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private Context context;
        public MyAdapter(Context context)
        {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // How many items are in the data set represented by this Adapter.(在此适配器中所代表的数据集中的条目数)
            return data.size();
        }
        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.(获取数据集中与指定索引对应的数据项)
            return data.get(position);
        }
        @Override
        public long getItemId(int position) {
            // Get the row id associated with the specified position in the list.(取在列表中与指定索引对应的行id)
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if(convertView == null)
            {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.item, null);

                holder.name = (TextView)convertView.findViewById(R.id.user_name);
                holder.id = (TextView)convertView.findViewById(R.id.user_id);

                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            }else
            {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.name.setText((String)data.get(position).get("name"));
            holder.id.setText((String)data.get(position).get("id"));

            return convertView;
        }
    }

}
