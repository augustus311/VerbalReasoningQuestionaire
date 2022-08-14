package com.example.firstquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import com.example.firstquiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FirstQuiz.db";
    private static final int DATABASE_VERSION = 23;


    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_ID + " INTEGER, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " +

                ")";


        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTs " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }


    private void fillQuestionsTable() {

        Question q1 = new Question("Instituted in 1979 as a temporary measure to limit population growth, China’s one child policy remains in force today and is likely to continue for another decade. China’s population control policy has attracted criticism because of the manner in which it is enforced, and also because of its social repercussions. Supporters of the Chinese government’s policy consider it a necessary measure to curb extreme overpopulation, which has resulted in a reduction of an estimated 300 million people in its first twenty years. Not only is a reduced population environmentally beneficial," +
                "it also increases China’s per capita gross domestic product. The one-child policy has" +
                "led to a disparate ratio of males to females – with abortion, abandonment and" +
                "infanticide of female infants resulting from a cultural preference for sons." +
                "Furthermore, Draconian measures such as forced sterilization are strongly opposed" +
                "by critics as a violation of human reproduction rights. The one-child policy is" +
                "enforced strictly in urban areas, whereas in provincial regions fines are imposed on" +
                "families with more than one child. There are also exceptions to the rules – for" +
                "example, ethnic minorities. A rule also allows couples without siblings to have two" +
                "children – a provision which applies to millions of sibling-free adults now of childbearing age.\n" +
                "\nChina’s one-child policy increases the country’s wealth.\n \nIs this statement true, false or cannot say?", "True", "False", "Cannot Say", " ", 1, 3);
        addQuestion(q1);

        Question q2 = new Question(" Instituted in 1979 as a temporary measure to limit population growth, China’s one" +
                "child policy remains in force today and is likely to continue for another decade." +
                "China’s population control policy has attracted criticism because of the manner in" +
                "which it is enforced, and also because of its social repercussions. Supporters of the" +
                "Chinese government’s policy consider it a necessary measure to curb extreme" +
                "overpopulation, which has resulted in a reduction of an estimated 300 million people" +
                "in its first twenty years. Not only is a reduced population environmentally beneficial, it" +
                "also increases China’s per capita gross domestic product. The one-child policy has" +
                "led to a disparate ratio of males to females – with abortion, abandonment and" +
                "infanticide of female infants resulting from a cultural preference for sons. Furthermore," +
                "Draconian measures such as forced sterilization are strongly opposed by critics as a" +
                "violation of human reproduction rights. The one-child policy is enforced strictly in" +
                "urban areas, whereas in provincial regions fines are imposed on families with more" +
                "than one child. There are also exceptions to the rules – for example, ethnic minorities." +
                "A rule also allows couples without siblings to have two children – a provision which" +
                "applies to millions of sibling-free adults now of child-bearing age.\n " +
                "\nThe passage suggests that two-child families will dramatically increase, as sibling-free adults reach child-bearing age.\n" +
                "\nIs this statement true, false or cannot say?", "True", "False", "Cannot Say", " ",  2, 2);
        addQuestion(q2);

        Question q3 = new Question(" Instituted in 1979 as a temporary measure to limit population growth, China’s one\n" +
                "child policy remains in force today and is likely to continue for another decade." +
                "China’s population control policy has attracted criticism because of the manner in" +
                "which it is enforced, and also because of its social repercussions. Supporters of the" +
                "Chinese government’s policy consider it a necessary measure to curb extreme" +
                "overpopulation, which has resulted in a reduction of an estimated 300 million people" +
                "in its first twenty years. Not only is a reduced population environmentally beneficial, it" +
                "also increases China’s per capita gross domestic product. The one-child policy has" +
                "led to a disparate ratio of males to females – with abortion, abandonment and" +
                "infanticide of female infants resulting from a cultural preference for sons. Furthermore," +
                "Draconian measures such as forced sterilization are strongly opposed by critics as a" +
                "violation of human reproduction rights. The one-child policy is enforced strictly in" +
                "urban areas, whereas in provincial regions fines are imposed on families with more" +
                "than one child. There are also exceptions to the rules – for example, ethnic minorities." +
                "A rule also allows couples without siblings to have two children – a provision which" +
                "applies to millions of sibling-free adults now of child-bearing age.\n" +
                "\nThe main criticism of China’s one-child policy is that it violates human rights.\n" +
                "\nIs this statement true, false or cannot say?", "True", "False", "Cannot Say", " ",  3, 3);
        addQuestion(q3);

        Question q4 = new Question("Instituted in 1979 as a temporary measure to limit population growth, China’s one" +
                "child policy remains in force today and is likely to continue for another decade." +
                "China’s population control policy has attracted criticism because of the manner in" +
                "which it is enforced, and also because of its social repercussions. Supporters of the" +
                "Chinese government’s policy consider it a necessary measure to curb extreme" +
                "overpopulation, which has resulted in a reduction of an estimated 300 million people" +
                "in its first twenty years. Not only is a reduced population environmentally beneficial, it" +
                "also increases China’s per capita gross domestic product. The one-child policy has" +
                "led to a disparate ratio of males to females – with abortion, abandonment and" +
                "infanticide of female infants resulting from a cultural preference for sons. Furthermore," +
                "Draconian measures such as forced sterilization are strongly opposed by critics as a" +
                "violation of human reproduction rights. The one-child policy is enforced strictly in" +
                "urban areas, whereas in provincial regions fines are imposed on families with more" +
                "than one child. There are also exceptions to the rules – for example, ethnic minorities." +
                "A rule also allows couples without siblings to have two children – a provision which" +
                "applies to millions of sibling-free adults now of child-bearing age.\n" +
                "\nFamilies with more than one child are more common in China’s rural areas.\n" +
                "\nIs this statement true, false or cannot say?", "True", "False", "Cannot Say", "",  4, 3);
        addQuestion(q4);

        Question q5 = new Question("Instituted in 1979 as a temporary measure to limit population growth, China’s one" +
                "child policy remains in force today and is likely to continue for another decade." +
                "China’s population control policy has attracted criticism because of the manner in" +
                "which it is enforced, and also because of its social repercussions. Supporters of the" +
                "Chinese government’s policy consider it a necessary measure to curb extreme" +
                "overpopulation, which has resulted in a reduction of an estimated 300 million people" +
                "in its first twenty years. Not only is a reduced population environmentally beneficial, it" +
                "also increases China’s per capita gross domestic product. The one-child policy has" +
                "led to a disparate ratio of males to females – with abortion, abandonment and" +
                "infanticide of female infants resulting from a cultural preference for sons. Furthermore," +
                "Draconian measures such as forced sterilization are strongly opposed by critics as a" +
                "violation of human reproduction rights. The one-child policy is enforced strictly in" +
                "urban areas, whereas in provincial regions fines are imposed on families with more" +
                "than one child. There are also exceptions to the rules – for example, ethnic minorities." +
                "A rule also allows couples without siblings to have two children – a provision which" +
                "applies to millions of sibling-free adults now of child-bearing age.\n" +
                "\nThe general preference among Chinese parents is for male babies.\n" +
                "\nIs this statement true, false or cannot say?", "True", "False", "Cannot Say", " ",  5, 1);
        addQuestion(q5);

        Question q6 = new Question("There are 562 federally recognized American Indian tribes, with a total of" +
                "1.7 million members. Additionally, there are hundreds of groups seeking federal" +
                "recognition – or sovereignty – though less than ten percent will successfully achieve" +
                "this status. Federally recognised tribes have the right to self-government, and are also" +
                "eligible for federal assistance programmes. Exempt from state and local jurisdiction," +
                "tribes may enforce their own laws, request tax breaks and control regulatory activities." +
                "There are however limitations to their sovereignty including, amongst others, the ability" +
                "to make war and create currency. Historically, tribes were granted federal recognition" +
                "through treaties or by executive order. Since 1978 however, this has been replaced by" +
                "a lengthy and stringent regulatory process which requires tribes applying for federal" +
                "recognition to fulfil seven criteria, such as anthropological and historical evidence. One" +
                "of the complications regarding federal recognition is the legal definition of “Indian”." +
                "Previously, racial criteria, tribal records and personal affidavits were used to classify" +
                "American Indians. Since the 1970s, however, there has been a shift to the use of a" +
                "political definition – requiring membership in a federally recognized tribe in order to" +
                "qualify for benefits, such as loans and educational grants. This definition, however," +
                "excludes many individuals of Native American heritage who are not tribal members.\n" +
                "\nThere are only two exemptions to a federally recognized tribe’s powers of selfgovernment.\n" +
                "\nIs this statement true, false or cannot say?", "True", "False", "Cannot Say", " ",  6, 2);
        addQuestion(q6);
        /*Question q7 = new Question("What does the law require you to keep in good condition?", "Gears", "Transmission", "Door locks", "Seat belts", 4);
        addQuestion(q7);
        Question q8 = new Question("You keep well back while waiting to overtake a large vehicle. What should you do if a car moves into the gap?", "Sound your horn", "Drop back further", "Flash your headlights", "Start to overtake", 4);
        addQuestion(q8);
        Question q9 = new Question("A driver pulls out of a side road in front of you, causing you to brake hard. What should you do?", "Ignore the error and stay calm", "Flash your lights to show your annoyance", "Sound your horn to show your annoyance", "Overtake as soon as possible", 1);
        addQuestion(q9);
        Question q10 = new Question("On what type of road surface may anti-lock brakes not work effectively?", "Dry", "Loose", "Firm", "Smooth", 2);
        addQuestion(q10);
        Question q11 = new Question("Your vehicle has a puncture on a motorway. What should you do?", "Drive slowly to the next service area to get assistance", "Pull up on the hard shoulder. Change the wheel as quickly as possible", "Pull up on the hard shoulder. Use the emergency phone to get assistance", "Switch on your hazard warning lights. Stop in your lane", 3);
        addQuestion(q11);
        Question q12 = new Question("You're turning left into a side road. What hazard should you be especially aware of?", "One-way street", "Pedestrians", "Traffic congestion", "Parked vehicles", 2);
        addQuestion(q12);
        Question q13 = new Question("When will you feel the effects of engine braking?", "When you only use the handbrake", "When you're in neutral", "When you change to a lower gear", "When you change to a higher gear", 3);
        addQuestion(q13);
        Question q14 = new Question("You're on a motorway. There's a contraflow system ahead. What would you expect to find?", "Temporary traffic lights", "Lower speed limits", "Wider lanes than normal", "Speed humps", 2);
        addQuestion(q14);
        Question q15 = new Question("What information is found on a vehicle registration document?", "The registered keeper", "The type of insurance cover", "The service history details", "The date of the MOT", 1);
        addQuestion(q15);
        Question q16 = new Question("What should you do when moving off from behind a parked car?", "Give a signal after moving off", "Check both interior and exterior mirrors", "Look around after moving off", "Use the exterior mirrors only", 2);
        addQuestion(q16);
        Question q17 = new Question("You're approaching a red traffic light. What will the signal show next?", "Red and amber", "Green alone", "Amber alone", "Green and amber", 1);
        addQuestion(q17);
        Question q18 = new Question("You think the driver of the vehicle in front has forgotten to cancel their right indicator. What should you do?", "Flash your lights to alert the driver", "Sound your horn before overtaking", "Overtake on the left if there's room", "Stay behind and don't overtake", 4);
        addQuestion(q18);
        Question q19 = new Question("What should you do when overtaking a motorcyclist in strong winds?", "Pass closely", "Pass wide", "Pass very slowly", "Pass immediately", 2);
        addQuestion(q19);
        Question q20 = new Question("You've just passed your practical test. You don't hold a full licence in another category. Within two years you get six penalty points on your licence. What will you have to do?", "Retake only your theory test", "Retake only your practical test", "Retake your theory and practical tests", "Reapply for your full licence immediately", 3);
        addQuestion(q20);
        Question q21 = new Question("What's the main benefit of driving a four-wheel-drive vehicle?", "Improved grip on the road", "Shorter stopping distances", "Lower fuel consumption", "Improved passenger comfort", 1);
        addQuestion(q21);
        Question q22 = new Question("What's the right-hand lane used for on a three-lane motorway?", "Emergency vehicles only", "Vehicles towing trailers", "Overtaking", "Coaches only", 3);
        addQuestion(q22);
        Question q23 = new Question("You're following a slower-moving vehicle on a narrow country road. There's a junction just ahead on the right. What should you do?", "Overtake after checking your mirrors and signalling", "Accelerate quickly to pass before the junction", "Only consider overtaking when you're past the junction", "Slow down and prepare to overtake on the left", 3);
        addQuestion(q23);
        Question q24 = new Question("What colour are the reflective studs between a motorway and its slip road?", "Amber", "Green", "White", "Red", 2);
        addQuestion(q24);
        Question q25 = new Question("You're driving on the motorway in windy conditions. What should you do as you pass a high-sided vehicle?", "Increase your speed", "Drive alongside very closely", "Be wary of a sudden gust", "Expect normal conditions", 3);
        addQuestion(q25);*/

    }


    private void addQuestion(Question question) {

        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_ID, question.getAnswerId());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);

    }

    public ArrayList<Question> getAllQuestions() {

        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {

            do {

                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_ID)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);

            } while (c.moveToNext());
        }

        c.close();
        return questionList;

    }


}
