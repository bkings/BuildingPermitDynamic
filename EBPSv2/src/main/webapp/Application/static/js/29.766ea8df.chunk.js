(window.webpackJsonp=window.webpackJsonp||[]).push([[29],{992:function(e,a,t){"use strict";t.r(a);var r=t(55),n=t.n(r),s=t(99),i=t(36),o=t(37),p=t(41),l=t(40),c=t(0),u=t.n(c),m=t(599),d=t(348),f=t(781),h=t(129),b=t(180),v=t(24),E=t(78),g=t(240),j=t(130),D=t(608),N=t(602),w=t(612),F=t(603),O=t(605),S=t(607),x={heading:{head1:"\u092e\u0947\u091a\u0940 \u0928\u0917\u0930\u092a\u093e\u0932\u093f\u0915\u093e",head2:"\u0928\u0917\u0930 \u0915\u093e\u0930\u094d\u092f\u092a\u093e\u0932\u093f\u0915\u093e\u0915\u094b \u0915\u093e\u0930\u094d\u092f\u093e\u0932\u092f",subhead1:"\u0907\u0901\u091f\u093e\u092d\u091f\u094d\u091f\u093e, \u091d\u093e\u092a\u093e",subhead2:"\u0967 \u0928. \u092a\u0926\u0947\u0936 \u0928\u0947\u092a\u093e\u0932"},faxno:{faxname:"\u092b\u094d\u092f\u093e\u0915\u094d\u0938 :",faxn1:"\u0966\u0968\u0969-\u096b\u096c\u0968\u0968\u0967\u0969",faxn2:"\u0966\u0968\u0969-\u096b\u096c\u0968\u0968\u0967\u0968",faxn3:"\u0966\u0968\u0969-\u096b\u096c\u0968\u096a\u0969\u096d",faxn4:"\u0966\u0968\u0969-\u096b\u096c\u0968\u0968\u0969\u0969",faxn5:"\u0966\u0968\u0969-\u096b\u096c\u0968\u0966\u096d\u096d"},tip:"\u091f\u093f\u092a\u094d\u092a\u0923\u0940 / \u0906\u0926\u0947\u0936",topic:"\u0938\u0902\u0938\u094b\u0927\u0928\u0915\u094b \u091f\u093f\u092a\u094d\u092a\u0923\u0940 \u0906\u0926\u0947\u0936"},C=j.object().shape({uploadFile:N.g}),k=function(e){Object(p.a)(t,e);var a=Object(l.a)(t);function t(e){var r;Object(i.a)(this,t),r=a.call(this,e);var n={};try{n=JSON.parse(r.props.prevData.jsonData)}catch(p){n={}}var s=Object(E.z)({obj:n,reqFields:[]}),o=Object(v.g)(r.props.otherData.fileCategories,r.props.parentProps.location.pathname).fileCatId;return r.state={initialValues:s,fileCatId:o},r}return Object(o.a)(t,[{key:"render",value:function(){var e=this,a=this.props,t=a.prevData,r=a.hasSavePermission,i=a.formUrl,o=a.hasDeletePermission,p=a.isSaveDisabled,l=this.state,c=l.initialValues,v=l.fileCatId,j=this.props.userData;return u.a.createElement(u.a.Fragment,null,u.a.createElement(h.d,{initialValues:c,validationSchema:C,onSubmit:function(){var a=Object(s.a)(n.a.mark(function a(r,s){var i,o,p,l,c;return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(i=s.setSubmitting,r.applicationNo=e.props.permitData.applicantNo,o=new FormData,p=r.uploadFile,l=!1,p){for(c=0;c<p.length;c++)o.append("file",p[c]);o.append("fileType",v),o.append("applicationNo",e.props.permitData.applicantNo),l=!0,r.uploadFile&&delete r.uploadFile}return a.prev=6,a.next=9,e.props.postAction(b.a.sansodhankoTippaniAdesh,r,b.a.fileUpload,o,l);case 9:window.scroll(0,0),e.props.success&&e.props.success.success&&(Object(E.u)(Object(E.c)(t),e.props.parentProps,e.props.success),i(!1)),i(!1),a.next=18;break;case 14:a.prev=14,a.t0=a.catch(6),i(!1),console.log("Error",a.t0);case 18:case"end":return a.stop()}},a,null,[[6,14]])}));return function(e,t){return a.apply(this,arguments)}}(),render:function(a){var n=a.values,s=a.isSubmitting,l=a.handleSubmit,c=a.setFieldValue,h=a.errors,b=a.validateForm;return u.a.createElement(m.a,{loading:s},e.props.errors&&u.a.createElement(g.b,{message:e.props.errors.message}),u.a.createElement("div",{ref:e.props.setRef},u.a.createElement("div",{className:"NJ-Main superStruConsView"},u.a.createElement("div",{className:"tipandi-letterHead"},u.a.createElement(w.b,{userInfo:j})),u.a.createElement(S.a,null,u.a.createElement("h2",{className:"underline end-section"},x.tip)),u.a.createElement(d.a,{as:"h3"},u.a.createElement(f.a,{horizontal:!0},x.topic)),u.a.createElement("p",null,u.a.createElement(D.h,{placeholder:"\u0928\u093f\u0935\u0947\u0926\u0928",name:"nibedhan",setFieldValue:c,value:n.nibedhan})))),u.a.createElement(O.b,{errors:h,validateForm:b,formUrl:i,hasSavePermission:r,hasDeletePermission:o,isSaveDisabled:p,prevData:Object(E.c)(t),handleSubmit:l}))}}))}}]),t}(c.Component);a.default=function(e){return u.a.createElement(F.a,{api:[{api:b.a.sansodhankoTippaniAdesh,objName:"sansodhankoTippaniAdesh",form:!0},{api:b.a.fileCategories,objName:"fileCategories",form:!1,utility:!0}],onBeforeGetContent:{param1:["getElementsByTagName","textarea","value"],param2:["getElementsByClassName","ui input","value"]},prepareData:function(e){return e},parentProps:e,useInnerRef:!0,hasFile:!0,render:function(a){return u.a.createElement(k,Object.assign({},a,{parentProps:e}))}})}}}]);
//# sourceMappingURL=29.766ea8df.chunk.js.map