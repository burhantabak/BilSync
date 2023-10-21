import { createContext, useContext, useMemo } from "react";

const DataContext = createContext();

export const DataProvider = ({ children }) => {
  const postList = [
    {
      userName: "Tuna Saygın",
      title: "CS 319 Hoca Değerlendirmesi",
      description: "Hangi hocadan almak daha mantıklı",
      vote: 16,
      isTrading: false,
      isLostnFound: false,
      price: null,
      IBAN: null,
      comments: [
        {
          userName: "Burhan",
          text: "Eray Hoca'dan al mükemmel anlatıyor",
          likeNo: 10,
          isReply: false
        },
        {
          userName: "Işıl",
          text: "Katılıyorum",
          likeNo: 3,
          isReply: true
        },
        {
          userName: "Tuna",
          text: "tşk",
          likeNo: 0,
          isReply: true
        }
      ]
    },
    {
      userName: "Kenan Zeynalov",
      title: "81 Sigara Alanı Kayıp Çakmak",
      description: "18 Ekimde 81 sigara alanında 20 gibi çakmağımı unutmuşum. Bulan varsa çok sevinirim",
      vote: 3,
      isTrading: false,
      isLostnFound: true,
      price: null,
      IBAN: null,
      comments: [
        {
          userName: "Ahmet Tarık",
          text: "Rengi neydi",
          likeNo: 2,
          isReply: false
        },
        {
          userName: "Kenan Zeynalov",
          text: "Metalik",
          likeNo: 1,
          isReply: true
        },
        {
          userName: "Tuna",
          text: "Geçmiş olsun kardeşim",
          likeNo: 3,
          isReply: false
        }
      ]
    },
    {
      userName: "Burhan Tabak",
      title: "Basys Satıyom",
      description: "2 dönem kullanılmış 2. el basys. CS223 ve Cs 224'cüler için",
      vote: 3,
      isTrading: true,
      isLostnFound: false,
      price: 30000,
      IBAN: "TR 203094090030",
      comments: [
        {
          userName: "Elhan",
          text: "Bütün switchler çalışıyor mu",
          likeNo: 2,
          isReply: false
        },
        {
          userName: "Burhan Tabak",
          text: "yes",
          likeNo: 1,
          isReply: true
        }
      ]
    }
  ];
  const chatList = [
    {
      userName: "Tuna Saygın",
      isGroupChat: false,
      messages: [
        {
          message: "Merhabalar hocam yarın CS319 dersi olacak mı?",
          date: "15th October 20:53",
          isReceived: true,
          userName: "Eray Tüzün",
        },
        {
          message: "Evet, yarın dersimiz olacak. Saat 14:30'da buluşalım.",
          date: "15th October 21:05",
          isReceived: false,
        },
        {
          message: "Tamamdır, görüşmek üzere.",
          date: "15th October 21:06",
          isReceived: true,
        },
      ],
    },
    {
      userName: "Kenan Zeynalov",
      isGroupChat: false,
      messages: [
        {
          message: "Günaydın, ders materyalleri paylaşılacak mı?",
          date: "16th October 08:30",
          isReceived: true,
          userName: "Eray Tüzün",
        },
        {
          message: "Evet, hemen paylaşacağım.",
          date: "16th October 08:35",
          isReceived: false,
        },
      ],
    },
    {
      userName: "CS Dream Group",
      isGroupChat: true,
      messages: [
        {
          message: "Bugün laboratuvar çalışması ile ilgili toplantımız var.",
          date: "16th October 14:00",
          isReceived: true,
        },
        {
          message: "Evet, hatırlatma için teşekkür ederim. Gerekli hazırlıkları yapacağım.",
          date: "16th October 14:05",
          isReceived: false,
        },
      ],
    },
    // Add more entries as needed
  ];
  const value = useMemo(() => ({ postList, chatList }), [postList,chatList]);

  return <DataContext.Provider value={value}>{children}</DataContext.Provider>;
};

export const useData = () => {
  return useContext(DataContext);
};
