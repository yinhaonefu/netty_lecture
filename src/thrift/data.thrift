namespace java thrift.generated
namespace py py.thrift.generated

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person{
    1: optional String username,
    2: optional int age,
    3: optional boolean married
}

exception DataException{
    1: optional String message,
    2: optional String callStack,
    3: optional String date
}

service PersonService{
    Person getPersonByUsername(1: required String username) throws (1: DataException dateException),
    void savePerson(1: required Person person) throws (1: DataException dateException)
}

// windows开发环境执行 thrift.exe --gen java src/thrift/data.thrift 编译生成代码
// mac环境执行 thrift --gen java src/thrift/data.thrift
// mac中可以通过homebrew安装thrift编译器 brew install thrift
// 生成的源代码自动放到src/main/java/下，路径会自动拼接data.thrift中定义的namespace java路径 建议使用git subtree管理更新thrift生成的源代码，而不是每次生成复制粘贴
// thrift.exe已下载并放到项目netty_lecture根目录下
// 下载链接http://www.apache.org/dyn/closer.cgi?path=/thrift/0.10.0/thrift-0.10.0.exe
