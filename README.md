<div dir="rtl" align="right">

# <span dir="ltr">SDK</span> جاوا درگاه زیبال

## معرفی

این کتابخانه یک <span dir="ltr">SDK</span> سبک برای اتصال به درگاه پرداخت اینترنتی زیبال در پروژه‌های <span dir="ltr">
Spring Boot 3.5.7</span> است. تمرکز آن روی API ساده، اعتبارسنجی زودهنگام (<span dir="ltr">fail-fast</span>) و دریافت
پاسخ‌های تایپ‌شده است.

این پروژه یک کتابخانه است و برنامه اجرایی ندارد؛ آن را در پروژه خود استفاده می‌کنید تا روی منطق کسب‌وکار تمرکز کنید.

---

## پیش‌نیازها

* <span dir="ltr">Java 21</span>
* <span dir="ltr">Spring Boot 3.5.7</span>

---

## نصب

### مرحله ۱: ساخت و نصب محلی

ابتدا کتابخانه را در مخزن محلی <span dir="ltr">Maven</span> نصب کنید:

<div dir="ltr" align="left">

```bash
mvn clean install
```

</div>

### مرحله ۲: افزودن به پروژه مصرف‌کننده

<div dir="ltr" align="left">

```xml
<dependency>
  <groupId>com.ernoxin</groupId>
  <artifactId>zibal-java-sdk</artifactId>
  <version>1.0.0</version>
</dependency>
```

</div>

---

## پیکربندی

پیکربندی به‌صورت <span dir="ltr">fail-fast</span> انجام می‌شود: اگر مقدارهای اجباری ناقص باشند، برنامه در زمان بالا آمدن
متوقف می‌شود تا خطا به مرحله پرداخت نرسد.

### کلیدهای <span dir="ltr">application.properties</span>

| کلید                                              | الزامی | پیش‌فرض                                           | توضیح                                                                                |
|---------------------------------------------------|-------:|---------------------------------------------------|--------------------------------------------------------------------------------------|
| <span dir="ltr">`zibal.merchant`</span>           |    بله | -                                                 | شناسه پذیرنده؛ برای تست می‌توانید مقدار <span dir="ltr">`zibal`</span> استفاده کنید. |
| <span dir="ltr">`zibal.callback-url`</span>       |    بله | -                                                 | آدرس بازگشت پس از پرداخت؛ باید <span dir="ltr">https</span> باشد.                    |
| <span dir="ltr">`zibal.base-url`</span>           |    خیر | <span dir="ltr">`https://gateway.zibal.ir`</span> | دامنه سرویس (در صورت نیاز قابل تغییر)                                                |
| <span dir="ltr">`zibal.timeout.connect`</span>    |    خیر | <span dir="ltr">`10s`</span>                      | مهلت اتصال                                                                           |
| <span dir="ltr">`zibal.timeout.read`</span>       |    خیر | <span dir="ltr">`30s`</span>                      | مهلت دریافت پاسخ                                                                     |
| <span dir="ltr">`zibal.retry.enabled`</span>      |    خیر | <span dir="ltr">`false`</span>                    | فعال‌سازی تلاش مجدد در خطاهای شبکه                                                   |
| <span dir="ltr">`zibal.retry.max-attempts`</span> |    خیر | <span dir="ltr">`1`</span>                        | تعداد تلاش‌ها در صورت فعال بودن <span dir="ltr">`retry`</span>                       |
| <span dir="ltr">`zibal.retry.backoff`</span>      |    خیر | <span dir="ltr">`0ms`</span>                      | وقفه بین تلاش‌ها                                                                     |
| <span dir="ltr">`zibal.http.user-agent`</span>    |    خیر | <span dir="ltr">`ZibalJavaSdk`</span>             | مقدار <span dir="ltr">User-Agent</span> در درخواست‌ها                                |
| <span dir="ltr">`zibal.min-amount`</span>         |    خیر | <span dir="ltr">`1000`</span>                     | حداقل مبلغ معتبر (ریال) برای اعتبارسنجی سمت کلاینت                                   |

### <span dir="ltr">timeout</span> و <span dir="ltr">retry</span>

* <span dir="ltr">`zibal.timeout.connect`</span> زمان برقراری اتصال است و شامل <span dir="ltr">TCP/SSL</span> می‌شود.
* <span dir="ltr">`zibal.timeout.read`</span> زمان انتظار برای دریافت پاسخ پس از اتصال است.
* <span dir="ltr">retry</span> فقط روی خطاهای شبکه/ارتباطی اجرا می‌شود و روی خطاهای منطقی درگاه یا کدهای نتیجه اجرا
  نمی‌شود.
* <span dir="ltr">`max-attempts`</span> تعداد کل تلاش‌ها و <span dir="ltr">`backoff`</span> فاصله بین تلاش‌ها را مشخص
  می‌کند.
* **هشدار:** فعال کردن <span dir="ltr">retry</span> برای درخواست پرداخت ممکن است باعث ایجاد چند <span dir="ltr">
  trackId</span> شود اگر درخواست اول ثبت شده ولی پاسخ آن به شما نرسیده باشد.

فرمت مدت‌زمان‌ها می‌تواند به صورت <span dir="ltr">`500ms`</span>، <span dir="ltr">`2s`</span> یا <span dir="ltr">
`1m`</span> باشد.

### نمونه تنظیمات

حداقل تنظیمات لازم:

<div dir="ltr" align="left">

```properties
zibal.merchant=zibal
zibal.callback-url=https://example.com/payment/callback
```

</div>

---

## آموزش گام‌به‌گام پرداخت

1. تزریق کلاینت
2. درخواست پرداخت
3. انتقال خریدار به درگاه
4. بازگشت و تایید پرداخت
5. استعلام (اختیاری)

### گام ۱: تزریق کلاینت

کلاینت به‌صورت خودکار ساخته می‌شود و کافی است آن را تزریق کنید:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.client.ZibalClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ZibalClient client;

}
```

</div>

### گام ۲: درخواست پرداخت

حداقل ورودی لازم فقط مبلغ است. سایر فیلدها اختیاری هستند.

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.model.PaymentRequest;
import com.ernoxin.zibaljavasdk.model.PaymentRequestResponse;

PaymentRequest request = PaymentRequest.builder(160_000)
        .description("Order #A-1001")
        .build();

PaymentRequestResponse response = client.requestPayment(request);
```

</div>

### گام ۳: انتقال خریدار به درگاه

<div dir="ltr" align="left">

```java
String paymentUrl = client.buildPaymentUrl(response.trackId());
```

</div>

### گام ۴: بازگشت و تایید پرداخت

در بازگشت، پارامترها را از <span dir="ltr">query string</span> بخوانید و فقط در صورت موفقیت، تایید را انجام دهید.

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.callback.ZibalCallback;
import com.ernoxin.zibaljavasdk.model.PaymentVerificationRequest;

public void handleCallback(Map<String, String> params) {
    ZibalCallback callback = client.parseCallback(params);
    if (!callback.success()) {
        throw new IllegalStateException("پرداخت ناموفق یا لغو شده است");
    }
    client.verifyPayment(PaymentVerificationRequest.of(callback.trackId()));
}
```

</div>

### گام ۵: استعلام (اختیاری)

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.model.PaymentInquiryRequest;
import com.ernoxin.zibaljavasdk.model.PaymentInquiryResponse;

PaymentInquiryResponse inquiry = client.inquirePayment(PaymentInquiryRequest.of(trackId));
```

</div>

---

## متد <span dir="ltr">Lazy</span>

در روش <span dir="ltr">Lazy</span>، زیبال به جای <span dir="ltr">GET</span>، اطلاعات را به صورت <span dir="ltr">
JSON</span> و با <span dir="ltr">POST</span> به <span dir="ltr">callbackUrl</span> ارسال می‌کند. برای این حالت از متدهای
زیر استفاده کنید:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.callback.ZibalLazyCallback;
import com.ernoxin.zibaljavasdk.model.PaymentRequestResponse;
import com.ernoxin.zibaljavasdk.model.PaymentVerificationRequest;
import com.ernoxin.zibaljavasdk.model.PaymentVerificationResponse;

PaymentRequestResponse response = client.requestLazyPayment(request);
PaymentVerificationResponse verify = client.verifyLazyPayment(PaymentVerificationRequest.of(trackId));
ZibalLazyCallback lazyCallback = client.parseLazyCallback(rawJsonBody);
```

</div>

---

## مدل‌ها و اعتبارسنجی

همه درخواست‌ها <span dir="ltr">merchant</span> را از تنظیمات می‌خوانند و در ورودی‌ها دریافت نمی‌کنند. اگر داده‌ها
نامعتبر باشند، <span dir="ltr">ZibalValidationException</span> قبل از ارسال درخواست پرتاب می‌شود.

### <span dir="ltr">PaymentRequest</span>

| فیلد (SDK)                                   | نوع                                           |           الزامی | توضیح                                              |
|----------------------------------------------|-----------------------------------------------|-----------------:|----------------------------------------------------|
| <span dir="ltr">`amount`</span>              | <span dir="ltr">long</span>                   |              بله | مبلغ تراکنش به ریال؛ باید حداقل ۱۰۰۰ باشد.         |
| <span dir="ltr">`callbackUrl`</span>         | <span dir="ltr">URI</span>                    | خیر (در درخواست) | در صورت عدم ارسال از مقدار تنظیمات استفاده می‌شود. |
| <span dir="ltr">`description`</span>         | <span dir="ltr">String</span>                 |              خیر | توضیحات تراکنش.                                    |
| <span dir="ltr">`orderId`</span>             | <span dir="ltr">String</span>                 |              خیر | شناسه سفارش شما.                                   |
| <span dir="ltr">`mobile`</span>              | <span dir="ltr">String</span>                 |              خیر | شماره موبایل خریدار.                               |
| <span dir="ltr">`allowedCards`</span>        | <span dir="ltr">List<String></span>           |              خیر | لیست کارت‌های مجاز (هر کارت ۱۶ رقمی).              |
| <span dir="ltr">`nationalCode`</span>        | <span dir="ltr">String</span>                 |              خیر | کد ملی ۱۰ رقمی.                                    |
| <span dir="ltr">`checkMobileWithCard`</span> | <span dir="ltr">Boolean</span>                |              خیر | تطبیق شماره کارت و موبایل.                         |
| <span dir="ltr">`percentMode`</span>         | <span dir="ltr">ZibalPercentMode</span>       |              خیر | حالت تسهیم درصدی (۰/۱).                            |
| <span dir="ltr">`feeMode`</span>             | <span dir="ltr">ZibalFeeMode</span>           |              خیر | نحوه کسر کارمزد (۰/۱).                             |
| <span dir="ltr">`multiplexingInfos`</span>   | <span dir="ltr">List<MultiplexingInfo></span> |              خیر | اطلاعات تسهیم.                                     |

---

## نکات مهم

* در مستندات رسمی فقط یک <span dir="ltr">base URL</span> معرفی شده است. اگر زیبال آدرس تست جداگانه ارائه داد، می‌توانید
  مقدار آن را در تنظیمات جایگزین کنید.
* زیبال فقط به درخواست‌های <span dir="ltr">HTTPS</span> پاسخ می‌دهد؛ به همین دلیل <span dir="ltr">base URL</span> باید
  حتماً <span dir="ltr">https</span> باشد.

---

## سرویس بانکداری شرکتی (<span dir="ltr">EBank</span>)

این بخش مربوط به سرویس بانکداری شرکتی زیبال است و به صورت جداگانه پیکربندی می‌شود. احراز هویت این سرویس با
هدر <span dir="ltr">Authorization: Bearer</span> انجام می‌شود و باید <span dir="ltr">Access Token</span> را از پنل
توسعه‌دهندگان دریافت کنید.

### کلیدهای <span dir="ltr">application.properties</span> برای <span dir="ltr">EBank</span>

| کلید                                                    | الزامی | پیش‌فرض                                       | توضیح                                       |
|---------------------------------------------------------|-------:|-----------------------------------------------|---------------------------------------------|
| <span dir="ltr">`zibal.ebank.access-token`</span>       |    بله | -                                             | توکن دسترسی برای احراز هویت                 |
| <span dir="ltr">`zibal.ebank.base-url`</span>           |    خیر | <span dir="ltr">`https://api.zibal.ir`</span> | دامنه سرویس بانکداری شرکتی                  |
| <span dir="ltr">`zibal.ebank.timeout.connect`</span>    |    خیر | <span dir="ltr">`10s`</span>                  | مهلت اتصال                                  |
| <span dir="ltr">`zibal.ebank.timeout.read`</span>       |    خیر | <span dir="ltr">`30s`</span>                  | مهلت دریافت پاسخ                            |
| <span dir="ltr">`zibal.ebank.retry.enabled`</span>      |    خیر | <span dir="ltr">`false`</span>                | تلاش مجدد در خطاهای شبکه                    |
| <span dir="ltr">`zibal.ebank.retry.max-attempts`</span> |    خیر | <span dir="ltr">`1`</span>                    | تعداد تلاش‌ها                               |
| <span dir="ltr">`zibal.ebank.retry.backoff`</span>      |    خیر | <span dir="ltr">`0ms`</span>                  | وقفه بین تلاش‌ها                            |
| <span dir="ltr">`zibal.ebank.http.user-agent`</span>    |    خیر | <span dir="ltr">`ZibalEbankJavaSdk`</span>    | مقدار <span dir="ltr">User-Agent</span>     |
| <span dir="ltr">`zibal.ebank.min-amount`</span>         |    خیر | <span dir="ltr">`10000`</span>                | حداقل مبلغ تسویه برای اعتبارسنجی سمت کلاینت |

### نمونه تنظیمات

<div dir="ltr" align="left">

```properties
zibal.ebank.access-token=YOUR_ACCESS_TOKEN
```

</div>

### نمونه استفاده

ثبت درخواست تسویه:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.ebank.client.ZibalEbankClient;
import com.ernoxin.zibaljavasdk.ebank.model.CheckoutCreateRequest;
import com.ernoxin.zibaljavasdk.ebank.model.CheckoutResponse;

CheckoutCreateRequest request = CheckoutCreateRequest.builder("your-account-id", 10000, "IR030170000565276560000000")
        .reasonCode(1)
        .uniqueCode("12")
        .description("تسویه کاربر")
        .build();

CheckoutResponse response = ebankClient.createCheckout(request);
```

</div>

استعلام تسویه:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.ebank.model.CheckoutInquireRequest;
import com.ernoxin.zibaljavasdk.ebank.model.CheckoutResponse;

CheckoutResponse inquire = ebankClient.inquireCheckout(
        CheckoutInquireRequest.builder("your-account-id")
                .uniqueCode("12")
                .build()
);
```

</div>

دریافت موجودی:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.ebank.model.BalanceRequest;
import com.ernoxin.zibaljavasdk.ebank.model.BalanceResponse;

BalanceResponse balance = ebankClient.getBalance(BalanceRequest.of("your-account-id"));
```

</div>

---

## پلتفرم جامع پرداختی (<span dir="ltr">Platform</span>)

این بخش مربوط به مستندات پلتفرم جامع پرداختی زیبال است و احراز هویت آن با <span dir="ltr">Bearer Token</span> انجام
می‌شود. برای استفاده از این سرویس باید <span dir="ltr">Access Token</span> معتبر از پنل توسعه‌دهندگان دریافت کنید.

### کلیدهای <span dir="ltr">application.properties</span> برای <span dir="ltr">Platform</span>

| کلید                                                       | الزامی | پیش‌فرض                                       | توضیح                                   |
|------------------------------------------------------------|-------:|-----------------------------------------------|-----------------------------------------|
| <span dir="ltr">`zibal.platform.access-token`</span>       |    بله | -                                             | توکن دسترسی برای احراز هویت             |
| <span dir="ltr">`zibal.platform.base-url`</span>           |    خیر | <span dir="ltr">`https://api.zibal.ir`</span> | دامنه سرویس پلتفرم                      |
| <span dir="ltr">`zibal.platform.timeout.connect`</span>    |    خیر | <span dir="ltr">`10s`</span>                  | مهلت اتصال                              |
| <span dir="ltr">`zibal.platform.timeout.read`</span>       |    خیر | <span dir="ltr">`30s`</span>                  | مهلت دریافت پاسخ                        |
| <span dir="ltr">`zibal.platform.retry.enabled`</span>      |    خیر | <span dir="ltr">`false`</span>                | تلاش مجدد در خطاهای شبکه                |
| <span dir="ltr">`zibal.platform.retry.max-attempts`</span> |    خیر | <span dir="ltr">`1`</span>                    | تعداد تلاش‌ها                           |
| <span dir="ltr">`zibal.platform.retry.backoff`</span>      |    خیر | <span dir="ltr">`0ms`</span>                  | وقفه بین تلاش‌ها                        |
| <span dir="ltr">`zibal.platform.http.user-agent`</span>    |    خیر | <span dir="ltr">`ZibalPlatformJavaSdk`</span> | مقدار <span dir="ltr">User-Agent</span> |

### نمونه تنظیمات

<div dir="ltr" align="left">

```properties
zibal.platform.access-token=YOUR_ACCESS_TOKEN
```

</div>

### نمونه استفاده

لیست کیف پول‌ها:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.platform.client.ZibalPlatformClient;
import com.ernoxin.zibaljavasdk.platform.model.WalletListResponse;

WalletListResponse wallets = platformClient.listWallets();
```

</div>

استعلام موجودی یک کیف پول:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.platform.model.WalletBalanceRequest;
import com.ernoxin.zibaljavasdk.platform.model.WalletBalanceResponse;

WalletBalanceResponse balance = platformClient.getWalletBalance(WalletBalanceRequest.of(1010101));
```

</div>

درخواست استرداد:

<div dir="ltr" align="left">

```java
import com.ernoxin.zibaljavasdk.platform.model.RefundRequest;
import com.ernoxin.zibaljavasdk.platform.model.RefundResponse;

RefundRequest refund = RefundRequest.builder(2808993485L, "your-account-id")
        .amount(30000L)
        .tryReverse(false)
        .build();

RefundResponse response = platformClient.createRefund(refund);
```

</div>

### نکات مهم

* پاسخ <span dir="ltr">`/v1/report/checkout/inquire`</span> و <span dir="ltr">`/v1/account/refund/inquiry`</span> وابسته
  به وضعیت است و ساختار ثابتی ندارد. برای این دو متد، فیلد <span dir="ltr">`data`</span> به صورت <span dir="ltr">
  JsonNode</span> در دسترس است.
* در مستندات نمونه‌هایی وجود دارد که با <span dir="ltr">schema</span> کاملاً همخوان نیستند (برای مثال برخی فیلدهای پاسخ
  ریفاند در نمونه آمده ولی در <span dir="ltr">schema</span> نیامده است). در صورت نیاز می‌توان این بخش‌ها را با توجه به
  نسخه رسمی مستندات تکمیل کرد.
