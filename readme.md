
# ViewBinder
轻量级安卓视图绑定框架

## 使用示例
### Activity
```java
public class MainActivity extends AppCompatActivity {
    @Bind
    TextView textView;
    @Bind
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 自动绑定View */
        ViewBinder.bind(this,R.id.class);
    }
}
```
### Fragment
```java
public class TestFragment extends Fragment {

    @Bind
    TextView textView;

    public TestFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* 自动绑定View */
        ViewBinder.bind(this,R.id.class);

        textView.setText("This is TestFragment TextView.");
    }

}
```
* **ViewBinder.bind(this,R.id.class)** : 在每个Activity界面的onCreate方法、每个Fragment界面的onViewCreated方法中写下这行，即可实现**成员变量**和**视图**的绑定。
* **@Bind** : 绑定注解，注明该成员变量由框架接管，可如下2种方式使用。
  * 无参：@Bind，在视图中寻找id与成员变量名相同的控件并绑定。
  * 带参：@Bind("xxx")，在视图中寻找id为xxx的控件并绑定。