# mmvm-android ders notlarım


> - 1.) klasör yapısı oluştur
> - - ui (tüm featureları burada kategorize et)
> - -  di (dependency injection)
> - -  util (bağımsız classlar için, shared gibi)
> - -  data veya models diye bir klasör aç -local ve remote klasörü aç
> - 2 ) application class oluştur (manifesye name olarak tanımla) => annotation ekle
> - 3 ) dagger hilt ekle
> - 4 ) MainActivity annotation ekle
> - 5 ) ViewBinding aktif et
> - 6 ) refrofit ekle
> - 7 ) biz data ile uğraştığımızdan bu datanın state ini görebilmek için "Resource Folder" ekle Util klasörüne
> - 8 ) di klasörü içerisinde Network Module class aç => dagger ın bu instanceları diğer classlara taşıması için kullanmamız gerekn bir kavram. Burada tanımladığımmız herşey dışarıdan provide edilebilir olacak => @Modulu
Dagger 2 olmasaydı bu class için component annotation açıp hangi componentlerde kullanılması gerektiğiniz söylememiz gerekecekti Onun yerine @InstallIn (component adı yzılırdı)
> - - 8.a ) classların önüne genelde provide eklenir
> - 9 ) Network Module içerisinde Retrofit helper, okhttpclient yazılır @Provies annotation eklenir
> - 10 ) Retrofitten servis üretmemiz gerekiyor bunu Retrofit nesnesi oluşturarak yapalım data =>remote altına burada get-post işlemlerini burada yapıyoruz .
> - 10.a ) geliyoruz bu serviside NetworkModule de provide ediyoruz
> - 11 ) network Modulede kullanılan classları inject etmek içik kullanmak istediğim yerde @Inject annotaion ekleriz
> - 12 ) data için MODEL  klasörü içine objeleriimi oluşturuyoruz
-------------------------------------------
> - 13 ) call yeriine Corutine kullanmya başladık
istek atmamız gerekiyor bunun için bir launcher oluşturuyoruz. Bu launcher UI veya IO threadini alabilir
Uı threadden view e eriişebilir daha sonra background contexte geçip datayı load ediyoruz buda bizim nonUI thread geçişimizi sağlıyor. UI ve backgroud threadlerini geçişlerini daha kolay daha doğru bir şekilde handle etmek için kurulu bir yapımız olması gerekiyor. Bu yapılarda biri suspend function diğeri ise launcher dır
diyelimki suspend function ile fetchUser isteği atılacağı zaman UI kitlemeden backgroundda işlem yaparak ui kitlenmez
fakat suspend functionlar UI metodları altından ulaşılabilir değiller. bu gibi fonksiyonun ulaşılabilir olması için suspend funtion ile yönetilebiliyor olması lazım. Bunu da viewModel altında yönetiyor olacağız
-----------------------------------
> - 14 ) view modelleri kullanmak => repository katmanına ihtiyacımız olacak
repository katmanı bizim isteiğimizi oluşturacak
ilk öncelikle api repositorynin gerek duyduğu remoteDataSource ve localDataSource classlarını  oluşturalım

> - - 14a ) remote datasource retrofit kullancak biz burada NetworkApiServisimiz kullanmak için ctor olarak bu servisi inject edeceğiz

> - 15 ) ApiRepository class oluştur ve buraya remote ve local classlarını const içerisinde parametre olarak ver. bunları injectable olabilmesi için NetworkModulede bunlar provide et

> - 16 ) RemoteDataSource apiService inject et

> - - 16a ) api servicetelki listCharacteri çalıştırsın enqueue(callback)  => repository de kullanabiliyor hale geliyoruz

> - 17 ) ApiRepositoryde , RemoteDataSourcedan listCharacteri çekip UI'a suspend function yada LiveData döndürebiliriz

> - - 17a) burada ise get post işlemi yapan DATAACCESSSTRATGY kullanana bir metod çalıştırabilirz

------------------------------------
COROUITINE DEVREYE GİRİYOR => thread management olarak düşünebiliriz
corouitine projeye implement et

refactor yapalım biraz

> - 1 ) NetworkApiServicedeki metodları suspend yap. Callback yerine Response dönecek

> - 2 ) RemoteDataSourcedeki functionlarda suspend olamalı. 
   NOT : suspend functionları suspend functionlar çağırabilir.
   NOT : couritene olmasas biz apimizi callback veya rxJava ile yönetiyor olacaktık. Coruitinelerin JS'de karşılığı Promiselerdir 
   Callbacklerden kurtulmak için devreye Coroutinler devreye giriyor bunu da kullanmak için scopelar ve launcherlardevreye giriyor
----------------------------
BASE DATASOURCE EKLE => suspend function retrofit kütüphanesi sayesinde Resource dönüşüyor ve bize her istekte success veya error data dönüyor
eklenen base dataSource RemoteDataSource implement et . apiden gelen datayı get result içine ver

> - 2.repository gel remotedatasourcedan fetchlistCharacteri al
----------------------------
BURAYA KADAR DATACLASSTAN REPOSITORY'E kadar olan kısmı daha doğru yönetmekle ilgiliydi
> - 1 )Fragment ve Fragmente ait ViewMODEL oluşturalım. Daha sonra bu ViewModel'de api ve savedStateHandler'ı inject et. Frahmentte view modeli çağır

> **NOT : Eskide ViewModelFactory yazılırdı şimdi HİLT annotation sayesinde bugörevi ona bıraktık ve biz bunlarla uğraşmamış oluyoruz**

> - 2 ) viewModelimizde inject ettiğimiz apiRepositore ulaşıp fun içerisinde fetchListCharacter ulaşalım

> - 3 ) fetchListCharacter ulaşabilmek için ya suspend fun olman gerekiyor veya launch içerisinde olman lazım => bu bize liveData dönüyor olabilirdi ve UI update edebilirdi

DataaccessStrategy function yazalım => util altında yazalım
kotlinde istediğimiz heryer de bir metod yazabilirim => performNetworkOperation olsun bunun adı
 > **NOT : bu fonk yazarken jetpackten lifecycle kütüphanesini implement etmeyi unutma**

bunu yapmamızın sebebi repository katmanında datayı suspend olarak değil LiveData olarak gönderilmesini sağlamak. Bunu nasıl sağlıyoruz tabi ki performNetworkOperation çağırarak. önceden api katmanında yazmış olduğum function suspend function sileriz.

> - 2 ) viewModelde artık networkfun olduğu için  fun'lara : LiveData <Resource<RickAndMortyResponse>> = apiRepository.getCharacterList  olarak yazabilirm

> - 3 ) Viewde (HomeFragmentte) viewModelden
   viewModel.fetchRickAndMortList().observe(viewLifecycleOwner,Observer{
        //elimizde artık stateler var
})

> **özetlemek gerekirse ; bizim NetworkModule vardı api bağlı bu api RemoteDataSource bağlı bu remoteDataSource api ile haberleşip BaseDatsource sayesinde network isteğini tamamlayıp datayı almakla yükümlü datayı aldıktan sonra apiRepository'e gidip benim böyle datam var ve biz bunu RESOURCE tipte birşeye dönüştürmek istiyoruz. resource tipinde livedataya döndürüyoruz. daha sonra repository'e gidip bunu ui ile haberleştirmek için kullanıyor . buradan viewModele geçip ui immutable şekilde UI geçiyor**

 Not : UI'daki state yönetimini viewModelde de yapabilirdik
