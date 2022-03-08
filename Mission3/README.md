0) local 환경(개발환경), prod(상용환경) 분리

![](https://images.velog.io/images/myway00/post/e5bae34a-5182-4161-93a0-0d562380301f/image.png)

# 1) UserEntity - PostEntity (1:多 관계)
## 1-1 ) UserEntity 생성
```
public class UserEntity {
    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "userentity"
    )
    private List<PostEntity> postentitylist = new ArrayList<>();

```