insert into prepared_messages (
    code,
    title,
    text_before,
    media_type,
    telegram_file_id
)
values (
'NF_WELCOME_INTRO',
'Welcome intro', 'Вітаю тебе в просторі NeuroFit - системі тренувань, де ми працюємо не тільки з тілом, покращуючи зовнішній стан, а й з мозком, енергією та внутрішнім станом.',
'VIDEO',
'DQACAgIAAyEFAATTxwaAAAMCagr1V1oqdOOtcfXFHJSvFAoxW-kAAo-hAAIj3yFIccywav7Hc6c7BA'
       );

insert into prepared_messages (
    code,
    title,
    text_before,
    telegram_file_id,
    text_after,
    button_text,
    button_type,
    button_value
)
values (
'NF_GIFT_NEURO_PROPOSAL',
'Gift neuro proposal',
$$
Забирай подарунок - відео з нейро-вправами, які тренують:
• Концентрацію та переключення уваги
• Здатність швидше адаптуватися до нового
• Вміння не зависати перед рішеннями
• Координацію тіла й мозку одночасно
• Швидкість реакції та обробки інформації.

🎁 Твій подарунок тут:
$$, null, null, 'Забрати подарунок', 'CALLBACK', 'ВІДЕО КООРДИНАЦІЯ');

insert into prepared_messages (
    code,
    title,
    text_before,
    media_type,
    telegram_file_id
)
values (
'NF_GIFT_NEURO',
'Gift neuro',
$$
Коли мозок вчиться виконувати дві різні задачі одночасно - формуються нові нейронні зв’язки.
І з часом це помітно не лише у вправах, а й у житті:
• легше приймати рішення
• менше хаосу в голові
• швидше включення в нові дії
• більше впевненості в собі та своїх діях.

Я бачу сильні зміни і в себе, і в клієнтів, які вже спробували мою систему на собі.
Бо потенціал у цього напрямку - величезний. Розпочинай і ти!
$$,
'VIDEO',
'BQACAgIAAyEFAATTxwaAAAMDagr2FRYhLP4H5R5hz8d9P-ghU3gAAmaaAAJ3bTFIMa1ObeB44Mk7BA'
       );

insert into prepared_messages (
    code,
    title,
    text_before
)
values (
'NF_STAY_IN_BOT',
'Stay in bot',
'Залишайся в боті, бо протягом кількох днів я буду надсилати тобі корисні вправи, міні-практики та матеріали, які допоможуть краще зрозуміти свій стан і познайомитися з методом NeuroFit 💫');

insert into prepared_messages (
    code,
    title,
    text_before,
    telegram_file_id,
    text_after,
    button_text,
    button_type,
    button_value
)
values (
'NF_WHAT_IS_NEUROFIT',
'What is NeuroFit',
$$
Що таке нейрофітнес?

Нейрофітнес - це підхід, у якому ми тренуємо не тільки м’язи, а й
із залученням спеціальних вправ прокачуємо мозок. Задіюючі
різні відділи головного мозку, ми можемо покращувати наші
когнітивні навички, а саме:
• увагу та контроль
• память та координацію
• баланс та концентрацію
• і головне, заспокоювати нервову систему.

Коли мозок і тіло працюють злагоджено, то рухи стають
легшими, а тіло менш напруженим!
$$, null, null, 'Отримати відео', 'CALLBACK', 'ВІДЕО ДЛЯ ОЧЕЙ'
       );

insert into prepared_messages (
    code,
    title,
    text_before,
    media_type,
    telegram_file_id
)
values (
'NF_WHAT_IS_NEUROFIT_VIDEO',
'What is NeuroFit video',
null,
'VIDEO',
'BQACAgIAAyEFAATTxwaAAAMEagr4yHggKya0-ZlEBcDC1H85JV8AAnuaAAJ3bTFIgCoN_QABkjdlOwQ'
       );

insert into prepared_messages (
    code,
    title,
    text_before,
    telegram_file_id,
    text_after,
    button_text,
    button_type,
    button_value
)
values (
'NF_NERVOUS_SYSTEM_RELOAD',
'Nervous system reload',
$$
Ти помічала, що інколи:
• важко зосередитись
• немає енергії
• тіло ніби затиснуте, з’являється напруга в шиї, спині
• навіть після відпочинку немає легкості?

Дуже часто справа не лише у фізичній втомі, а у перевантаженні
нервової системи.

Нейро-вправи допомагають мозку та тілу працювати більш
злагоджено та спокійно!

Тому я підготувала короткі вправи для перезавантаження
нервової системи!
А в повній програмі NeuroFit ти отримаєш унікальні медитації від
психологині і йогині, що в комплексі дає значний прогрес.

Спробуй сьогодні 👇
$$, null, null, 'Отримати відео', 'CALLBACK', 'ВІДЕО ДИХАННЯ'
       );

insert into prepared_messages (
    code,
    title,
    text_before,
    media_type,
    telegram_file_id
)
values (
'NF_NERVOUS_SYSTEM_RELOAD_VIDEO',
'Nervous system reload video',
null,
'VIDEO',
'BQACAgIAAyEFAATTxwaAAAMJagr6NQABNLkrRCRcE3cedqsBhUCWAAKnmgACd20xSFbF0AltCSaPOwQ'
       );

insert into prepared_messages (
    code,
    title,
    text_before,
    media_type,
    telegram_file_id,
    text_after
)
values (
'NF_WHY_CREATE_NEUROFIT',
'Why create NeuroFit',
'Чому я створила NeuroFit?',
'VOICE',
'AwACAgIAAyEFAATTxwaAAAMpahl4euBptyXT06iQ1vgB3CfiVFcAAgmhAAIMMzBI81U1b3-Uz3c7BA',
null
       );

insert into prepared_messages (
    code,
    title,
    media_type,
    telegram_file_id
)
values (
'NF_WHY_CREATE_NEUROFIT_PHOTO',
'Why create NeuroFit photo',
'PHOTO',
'AgACAgQAAyEFAATTxwaAAAMhaguLjG2JvnOR7Ux0PZNwCoeW0HYAAsMPaxviOFlQQCR-oH_bYdQBAAMCAAN5AAM7BA'
       );

insert into prepared_messages (
    code,
    title,
    text_before,
    button_text,
    button_type,
    button_value
)
values (
'NF_EXERCISE_WITH_BALL',
'Exercise with ball',
$$
Відчуй різницю!

Перед виконанням цієї вправи зверни увагу:
• як ти стоїш
• чи є напруга в плечах
• наскільки легко рухатись
• чи важко зосередитись.

А тепер виконай вправи (знадобиться м’яч)👇
$$, 'Отримати відео', 'CALLBACK', 'ВІДЕО З МЯЧАМИ');

insert into prepared_messages (
    code,
    title,
    text_before,
    media_type,
    telegram_file_id,
    text_after
)
values (
'NF_EXERCISE_WITH_BALL_VIDEO',
'Exercise with ball video',
null,
'VIDEO',
'BQACAgIAAyEFAATTxwaAAAMPagr6TDDH3pkWP2-xtgvCUWfG4k8AAgybAAJ3bTFI_fbU7PyW3Vo7BA',
$$
І після - ще раз відчуй своє тіло 💗

Навіть 3–5 хвилин правильних нейро-вправ реально можуть
змінити:
• баланс
• легкість руху
• відчуття тіла
• рівень напруги
$$
       );

insert into prepared_messages (
    code,
    title,
    media_type,
    telegram_file_id
)
values (
'NF_PROGRAM_CONTENT_PHOTO',
'Program content',
'PHOTO',
'AgACAgQAAyEFAATTxwaAAAMrahl5RCS4XjqdxJXR4SzXO0X9j5AAArINaxt2HshQIS_OTcLrdN8BAAMCAAN4AAM7BA');

insert into prepared_messages (
    code,
    title,
    text_after,
    button_text,
    button_type,
    button_value
)
values (
'NF_PROGRAM_CONTENT',
'Program content',
$$
Що всередині NeuroFit:

• 4 модулі
• 14 тренувань в записі
• 3 руханки
• нейро-вправи
• 2 медитації від йогині та психологині
• практичні лекції та вебінари
• загальний чат з підтримкою та мотивацією
• додаткові матеріали

Для тих, хто хоче системний результат, що відчутно
покращує якість життя!

Програма підходить навіть якщо:
• ви давно не тренувались
• швидко втомлюєтесь
• хочете тренуватись ефективно, але без виснаження
• бажаєте удосконалити наявні результати.
$$,
'Дізнатись детальніше',
'URL',
'https://butenkofit.com/neurofit'
       );

insert into prepared_messages (
    code,
    title,
    text_before,
    media_type,
    telegram_file_id
)
values (
'NF_RESULTS_FEEDBACK',
'Results feedback',
$$
Після NeuroFit дівчата часто помічають:
• покращення відчуття тіла
• більше енергії
• покращення уваги та концентрації на роботі
• легкість у спині та шиї
• відчуття контакту з тілом

І найцінніше - це стан, коли тіло перестає бути важким та
втомленим!
$$,
'VIDEO',
'BAACAgQAAyEFAATTxwaAAAMvaiWEToXUTLHOamKF4lzMxNB6jm4AAisfAAIKqihRbqk66GDXtKs7BA'
       );

insert into prepared_messages (
    code,
    title,
    media_type,
    telegram_file_id
)
values (
'NF_PROMOCODE_OFFER_PHOTO',
'Promocode offer photo',
'PHOTO',
'AgACAgQAAyEFAATTxwaAAAMVagr6jzS-_HQm3-vf70Nb7lm2FqMAAkUPaxviOFlQo7JbKJo_OrQBAAMCAAN5AAM7BA');

insert into prepared_messages (
    code,
    title,
    text_after,
    button_text,
    button_type,
    button_value
)
values (
'NF_PROMOCODE_OFFER',
'Promocode offer',
$$
За ці дні ти вже трохи познайомилась із системою NeuroFit 💗

Але в програмі ми повноцінно тренуємось, не тільки виконуємо
окремі вправи! І щоб тобі було легше почати - я підготувала
<b>спеціальний ПРОМОКОД на участь -20%</b> 🎁

<b>Промокод: NEURO88</b>
(знижка діє 48 годин)
$$,
'Дізнатись про програму',
'URL',
'https://butenkofit.com/neurofit'
       );

insert into prepared_messages (
    code,
    title,
    media_type,
    telegram_file_id
)
values (
'NF_PROMOCODE_REMINDER_PHOTO',
'Promocode reminder photo',
'PHOTO',
'AgACAgQAAyEFAATTxwaAAAMtahl6ff9YCKO1GoLqkwrIs23UduMAArQNaxt2HshQp8P1lZ3-un4BAAMCAAN5AAM7BA'
       );

insert into prepared_messages (
    code,
    title,
    text_after,
    button_text,
    button_type,
    button_value
)
values (
'NF_PROMOCODE_REMINDER',
'Promocode reminder',
$$
Нагадую 💗
Твій промокод на програму NeuroFit діє лише до завтра ✨

<b>Промокод: NEURO88</b>

Це чудова можливість:
•  почати займатись м’яко та без виснаження
•  покращити стан тіла
•  стати більш зібраними та енергійними
$$,
'Приєднатись до NeuroFit',
'URL',
'https://butenkofit.com/neurofit');