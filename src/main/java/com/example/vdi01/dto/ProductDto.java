//package com.example.vdi01.dto;
//
//public class ProductDto {
//    @Getter
//    @Builder
//    @AllArgsConstructor
//    public static class Detail {
//        private Long id;
//        private String name;
//        private Long startPrice;
//        private Long categoryId;
//        private String content;
//        private Long instantPrice;
//        private LocalDateTime startDate;
//        private String successBidderId;
//        private Long successBid;
//        private LocalDateTime endDate;
//        private Long bidUnit;
//        private String sellerId;
//        private Long bidderCnt;
//        private Long imgCnt;
//    }
//
//    @Getter
//    @Builder
//    @AllArgsConstructor
//    public static class Summary {
//        private Long id;
//        private String name;
//        private Long startPrice;
//        private Long instantPrice;
//        private LocalDateTime endDate;
//        private String successBidderId;
//        private Long successBid;
//        private String sellerId;
//        private Long bidderCnt;
//        private Long imgCnt;
//    }
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static class Request {
//        private String name;
//        private Long startPrice;
//        private Long categoryId;
//        private String content;
//        private Long instantPrice;
//        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//        private LocalDateTime endDate;
//        private Long bidUnit;
//        private Long sellerId;
//        private Long imgCnt;
//
//        public Product toProduct() {
//            return Product.builder()
//                    .name(name)
//                    .startPrice(startPrice)
//                    .category(Category.builder()
//                            .id(categoryId)
//                            .build())
//                    .content(content)
//                    .instantPrice(instantPrice)
//                    .endDate(endDate)
//                    .bidUnit(bidUnit)
//                    .seller(Member.builder()
//                            .id(sellerId)
//                            .build())
//                    .imgCnt(imgCnt)
//                    .build();
//        }
//    }
//
//}