(window.webpackJsonp=window.webpackJsonp||[]).push([[15],{652:function(e,a,t){"use strict";t.d(a,"a",function(){return p});var n=t(0),r=t.n(n),o=t(554),l=t(563),i=t(566),s=t(555),c=t(585),m=i.b.permitApplicationFormView,p=function(e){var a=e.setFieldValue,t=e.values,n=e.errors,i=e.wardOptions;return r.a.createElement("div",{className:"petitionerDetails"},r.a.createElement("h5",{className:"formFeild-mainLabel"},m.petitioner_nibedak),r.a.createElement("div",{className:"petitionerSignature"},m.petitioner_signature),r.a.createElement("div",null,r.a.createElement(o.h,{label:m.petitioner_name,name:"nibedakName",setFieldValue:a,value:t.nibedakName,error:n.nibedakName})),r.a.createElement("div",null,r.a.createElement(o.h,{label:m.petitioner_age,name:"tol",setFieldValue:a,value:t.tol,error:n.tol})),r.a.createElement("div",null,r.a.createElement(o.h,{label:m.petitioner_municipal,name:"newMunicipal",value:t.newMunicipal,setFieldValue:a})),r.a.createElement("div",null,r.a.createElement(s.d,{label:m.petitioner_vadaNo,name:"nibedakTol",compact:!0,options:i})),r.a.createElement("div",null,r.a.createElement(o.h,{label:m.petitioner_road,name:"nibedakSadak",setFieldValue:a,error:n.nibedakSadak,value:t.nibedakSadak})),r.a.createElement("div",null,r.a.createElement(o.h,{label:m.petitioner_phone,name:"applicantMobileNo",setFieldValue:a,value:t.applicantMobileNo,error:n.applicantMobileNo})),r.a.createElement("div",null,r.a.createElement(l.d,{label:m.petitioner_date,value:t.applicantDate,disabled:!0})),r.a.createElement(c.a,{labelName:m.petitioner_additional,name:"nibedakAdditional",setFieldValue:a,value:t.name}))}},658:function(e,a,t){"use strict";var n=t(46),r=t.n(n),o=t(82),l=t(25),i=t(26),s=t(39),c=t(38),m=t(0),p=t.n(m),u=t(122),d=t(847),h=t(550),f=t(208),v=t(559),b=t(642),g=t(828),E=t(119),_=t(849),k=t(36),F=t(13),O=t(578),C=t.n(O),x=t(210),y=t(11),N=t(553),w=t(560),j=t(53),V=function(e){Object(s.a)(t,e);var a=Object(c.a)(t);function t(e){var n;return Object(l.a)(this,t),(n=a.call(this,e)).state={imageOpen:!1,src:"",existingPhoto:"",imageHash:Date.now()},n}return Object(i.a)(t,[{key:"render",value:function(){var e,a,t=this,n=this.props,r=n.permitData,o=n.loading,l=n.error,i=n.values,s=n.setFieldValue,c=this.state.imageHash;if(r){var m,d=r.photo;return m=Object(F.h)(d,"/"),p.a.createElement(p.a.Fragment,null,p.a.createElement("div",null,p.a.createElement(g.a,{horizontal:!0},y.r)," ",p.a.createElement(h.a.Field,{error:!!l},p.a.createElement(u.a,{type:"file",name:"photo",id:"file_photo",multiple:!1,value:void 0,onChange:function(e){var a=Array.from(e.target.files);s("photo",a);var t=a.map(function(e,a){return{file:e,id:a,src:window.URL.createObjectURL(e)}});s("imagePreviewUrl._photo",t)},validate:(e=!0,a=!Object(j.a)(d),a?N.s:e?N.c:N.s)}),l&&p.a.createElement(E.a,{pointing:!0,prompt:!0,size:"large"},l),Object(u.d)(i,"imagePreviewUrl._photo")&&p.a.createElement("div",{className:"previewFileUpload"},Object(u.d)(i,"imagePreviewUrl._photo").map(function(e,a){return p.a.createElement("div",{key:a,className:"eachPreviewFile"},p.a.createElement(w.f,{isInput:!0,src:e.src,alt:e.file.name,type:e.file.type,title:e.file.name}))}))),p.a.createElement(_.a,{relaxed:!0},p.a.createElement(_.a.Header,null,"Existing Files"),p.a.createElement("div",{className:"previewFileUpload"},d?p.a.createElement("div",{key:d,className:"eachPreviewFile viewFileSuccess",title:m},p.a.createElement("img",{src:"".concat(Object(k.c)()).concat(d,"?").concat(c),alt:"imageFile",onClick:function(){return t.setState({imageOpen:!0,src:"".concat(Object(k.c)()).concat(d,"?").concat(c)})}})):p.a.createElement(_.a.Item,null,"No files uploaded."))),p.a.createElement(C.a,{visible:this.state.imageOpen,onClose:function(){return t.setState({imageOpen:!1})},images:[this.state.src?{src:this.state.src,alt:"nibedak photo"}:{src:"".concat(Object(k.c)()).concat(this.state.existingPhoto),alt:"nibedak photo"}],zIndex:1e4})))}return p.a.createElement(x.b,{loading:o,errors:l})}}]),t}(p.a.Component),B=t(610),P=t(571),S=t(109),D=t(56),U=t(643),A=function(e){Object(s.a)(t,e);var a=Object(c.a)(t);function t(){var e;Object(l.a)(this,t);for(var n=arguments.length,i=new Array(n),s=0;s<n;s++)i[s]=arguments[s];return(e=a.call.apply(a,[this].concat(i))).state={files:[],gotPhoto:!1},e.handleSubmitModal=function(){e.props.handleClose(),e.props.history.push("/user/forms/design-approval")},e.handleFileSubmitModal=function(){var a=Object(o.a)(r.a.mark(function a(t,n){var l,i;return r.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(n.setSubmitting(!0),!(e.state.files.length>0&&(t.photo||e.props.savedPermitData.photo))){a.next=20;break}if(e.state.files.forEach(function(){var a=Object(o.a)(r.a.mark(function a(t){var n,o,l;return r.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(n=t.files,o=new FormData,l=e.props.savedPermitData.id,!n){a.next=17;break}return n.forEach(function(e){return o.append("file",e)}),o.append("fileType",t.fileCatId),o.append("applicationNo",l),a.prev=7,a.next=10,e.props.postFileValidation("".concat(Object(k.a)()).concat(S.a.fileUpload).concat(l),o);case 10:a.next=15;break;case 12:a.prev=12,a.t0=a.catch(7),console.log("File upload failed",a.t0);case 15:a.next=18;break;case 17:console.log("Please select a file before uploading.");case 18:case"end":return a.stop()}},a,null,[[7,12]])}));return function(e){return a.apply(this,arguments)}}()),e.setState({files:[]}),l=new FormData,!(i=t.photo)){a.next=18;break}return i.forEach(function(e){return l.append("photo",e)}),a.prev=8,a.next=11,e.props.postNibedakPhoto("".concat(S.a.buildPermit).concat(e.props.savedPermitData.id),l);case 11:a.next=16;break;case 13:a.prev=13,a.t0=a.catch(8),console.log("File upload failed",a.t0);case 16:a.next=19;break;case 18:Object(F.s)(e.props.savedPermitData.photo)||e.setState({gotPhoto:!0});case 19:n.setSubmitting(!1);case 20:case"end":return a.stop()}},a,null,[[8,13]])}));return function(e,t){return a.apply(this,arguments)}}(),e.handleFileChange=function(a){e.setState(function(e){return{files:Object(U.a)(e.files,a)}})},e}return Object(i.a)(t,[{key:"handleSuccess",value:function(){var e=this;this.props.handleFilesUploaded(),setTimeout(function(){Object(F.x)("File uploaded"),e.props.handleClose(),e.props.history.push("/user/forms/design-approval")},2e3)}},{key:"componentDidUpdate",value:function(e,a){this.props.success!==e.success&&this.props.success.success&&this.props.open&&this.handleSuccess(),this.state.gotPhoto!==a.gotPhoto&&this.state.gotPhoto&&this.handleSuccess()}},{key:"render",value:function(){var e=this;return p.a.createElement("div",null,p.a.createElement(u.c,{key:"file-upload",onSubmit:function(a,t){return e.handleFileSubmitModal(a,t)},render:function(a){var t=a.values,n=a.setFieldValue,r=a.isSubmitting,o=a.handleSubmit,l=a.errors;return p.a.createElement(d.a,{key:"save-confirmation",closeIcon:!0,open:e.props.open,onClose:e.props.handleClose},p.a.createElement(d.a.Header,null,v.j.uploadFileReminder),p.a.createElement(d.a.Content,{scrolling:!0},p.a.createElement(h.a,{loading:r},p.a.createElement(V,{permitData:e.props.savedPermitData,values:t,setFieldValue:n,error:l.photo}),p.a.createElement(b.a,{url:"/user/forms/map-permit-application-edit",fileCategories:e.props.fileCategories,handleFileChange:e.handleFileChange,hasDeletePermission:!1}))),p.a.createElement(d.a.Actions,null,p.a.createElement(f.a,{negative:!0,onClick:e.handleClose},"Exit"),p.a.createElement(f.a,{type:"submit",positive:!0,icon:"checkmark",labelPosition:"right",content:"Go Forward",onClick:o})))}}))}}]),t}(p.a.Component),M={postFileValidation:P.g,postNibedakPhoto:B.c};a.a=Object(D.b)(function(e){return{fileCategories:e.root.formData.fileCategories,formData:e.root.formData,errors:e.root.ui.errors}},M)(A)},836:function(e,a,t){"use strict";t.r(a);var n=t(25),r=t(26),o=t(39),l=t(38),i=t(0),s=t.n(i),c=t(122),m=t(535),p=t(550),u=t(121),d=t(851),h=t(638),f=t(208),v=t(554),b=t(593),g=t(566),E=t(13),_=t(56),k=t(610),F=t(571),O=t(575),C=t(563),x=t(599),y=t(53),N=t(49),w=t(12),j=t(11),V=t(74),B=t(93),P=t(581),S=t(621),D=t(644),U=t(645),A=t(647),M=t(646),T=t(657),L=t(658),H=t(652),R=t(588),I=t(27),J=t(559),z=t(648),G=t(622),q=t(574),W=t(564),K=t(608),Q=t(16),X=t(655),Y=t(656),Z=t(592),$=[{key:1,value:"\u0928\u093f\u091c\u0940",text:"\u0928\u093f\u091c\u0940"},{key:2,value:"\u0917\u0941\u0920\u0940",text:"\u0917\u0941\u0920\u0940"},{key:3,value:"\u0938\u093e\u091d\u093e",text:"\u0938\u093e\u091d\u093e"},{key:4,value:"\u0938\u0930\u0915\u093e\u0930\u0940",text:"\u0938\u0930\u0915\u093e\u0930\u0940"},{key:5,value:"\u0938\u0902\u092f\u0941\u0915\u094d\u0924",text:"\u0938\u0902\u092f\u0941\u0915\u094d\u0924"}],ee=[{key:1,value:"METRE",text:"\u092e\u093f\u091f\u0930"},{key:2,value:"FEET",text:"\u092b\u093f\u091f"}],ae=g.b.permitApplicationFormView,te=Object(E.m)(),ne=te?te.organization:{name:"",address:""},re=q.d?Q.b.METRE.LENGTH:j.i,oe={buildingJoinRoadType:[ae.form_step2.checkBox_option.option_1],buildingJoinRoadTypeOther:"",purposeOfConstructionOther:"",constructionTypeOther:"",mohada:[ae.form_step5.checkBox_option3.option_1],constructionFinishingOther:"",foharArrangementOther:"",newMunicipal:ne.name,applicantAddress:ne.address,naksawalaAddress:ne.address,floorUnit:re,floor:[],dhalUnit:re,highTensionLineUnit:re,pipelineUnit:re,pipeline:ae.form_step8.checkBox_option.option_1,doPipelineConnection:ae.form_step8.checkBox_option2.option_1,isHighTensionLine:ae.form_step9.checkBox_option.option_1,isLowTensionLine:ae.form_step9.checkBox_option.option_1,surrounding:[{side:1,sideUnit:re},{side:2,sideUnit:re},{side:3,sideUnit:re},{side:4,sideUnit:re}],purposeOfConstruction:ae.form_step5.checkBox_option.option_1,constructionType:ae.form_step5.checkBox_option2[0].value,constructionFinishing:ae.form_step5.checkBox_option4.option_1,dhalNikasArrangement:ae.form_step6.checkBox_option.option_1,foharArrangement:ae.form_step7.checkBox_option.option_1,applicantMs:G.b[0].value,ownershipName:$[0].value,landAreaType:B.r[0].value,spouseType:B.w[0],lat:27.700769,lng:85.30014,kittaNo:[""],landArea:[""],landDetails:[""]},le=function(e){Object(o.a)(t,e);var a=Object(l.a)(t);function t(){var e;Object(n.a)(this,t);for(var r=arguments.length,o=new Array(r),l=0;l<r;l++)o[l]=arguments[l];return(e=a.call.apply(a,[this].concat(o))).state={purposeOfConstruction:ae.form_step5.checkBox_option.option_2,constructionType:ae.form_step5.checkBox_option2[0].value,constructionFinishing:ae.form_step5.checkBox_option4.option_1,dhalNikasArrangement:ae.form_step6.checkBox_option.option_1,foharArrangement:ae.form_step7.checkBox_option.option_1,pipeline:ae.form_step8.checkBox_option.option_1,doPipelineConnection:ae.form_step8.checkBox_option2.option_1,isHighTensionLine:ae.form_step9.checkBox_option.option_1,isLowTensionLine:ae.form_step9.checkBox_option.option_1,inputPurpOfConsFilter:!1,inputConsFinishFilter:!1,inputDhalArrgmntFilter:!0,inputFoharArrgmntFilter:!1,inputPipelineArrgmntFilter:!0,inputElecArrgmntFilter:!0,inputRoadTypeFilter:!1,open:!1,filesUploaded:!1,saved:!1,mapPosition:[27.700769,85.30014],marker:{lat:27.700769,lng:85.30014},savedPermitData:"",wardOptions:[],hasBlocks:B.h[1].value},e.handleMarkerClick=function(a){e.setState({marker:a.latlng})},e.handleFindOnMap=function(a,t){a&&t&&e.setState({marker:{lat:a,lng:t},mapPosition:[a,t]})},e.handleModalOpen=function(){e.setState({open:!0})},e.handleClose=function(){e.setState({open:!1})},e.handleFilesUploaded=function(){e.setState({filesUploaded:!0})},e}return Object(r.a)(t,[{key:"componentDidMount",value:function(){var e=JSON.parse(Object(w.a)(j.C)).map(function(e){return{key:e.id,value:e.name,text:e.name}});this.props.getFileCategories(),this.setState({wardOptions:e})}},{key:"render",value:function(){var e=this;return Object(y.a)(oe.applicantDate)&&(oe.applicantDate=Object(N.c)(!0)),Object(y.a)(oe.newMunicipal)&&(oe.newMunicipal=ne.name),this.props.location.pathname.includes("purano")?oe.constructionType=ae.form_step5.checkBox_option2[1].value:oe.constructionType=ae.form_step5.checkBox_option2[0].value,s.a.createElement(s.a.Fragment,null,s.a.createElement(L.a,{fileCategories:this.props.fileCategories,savedPermitData:this.state.savedPermitData,open:this.state.open,success:this.props.success,handleClose:this.handleClose,history:this.props.history,handleFilesUploaded:this.handleFilesUploaded}),s.a.createElement("div",{className:"information-bar"},s.a.createElement(m.a,null,s.a.createElement("div",{className:"infoBarTitle"},s.a.createElement("p",null,ae.form_naksa)))),s.a.createElement("div",{className:"buildingPermit-applicationForm"},s.a.createElement(c.c,{initialValues:oe,validateOnBlur:!0,validationSchema:q.g?U.a:U.b,onSubmit:function(a,t){t.setSubmitting(!0);var n=Object(R.b)(a);e.props.postFormData(n).then(function(a){if(window.scroll(0,0),Object(E.s)(e.props.errors)){Object(E.x)("Data for build permit saved successfully. Please upload the necessary files.");var n=a.data.obj;Object(E.v)(n),e.props.setPermit(n).then(function(a){var t=a.data.menu,n=Object(V.i)();t.forEach(function(e){if(e.viewURL&&"/user/forms/forward-to-next"===e.viewURL.trim())try{e.formName="".concat(e.formName," ").concat(n.find(function(a){return String(a.id)===String(e.groupId)}).name)}catch(a){console.log("Unable to fetch group names master.",a)}}),Object(w.b)(j.q,JSON.stringify(t)),e.props.getTaskList()}).catch(function(e){console.log(e)}),e.setState({open:!0,saved:!0,savedPermitData:n})}else t.setErrors(e.props.errors.message);t.setSubmitting(!1)}).catch(function(e){t.setSubmitting(!1)})},render:function(a){var t=ae.form_step10.member_details,n=0;return s.a.createElement(p.a,{loading:a.isSubmitting},s.a.createElement(I.a,{when:e.state.saved&&!e.state.filesUploaded,message:function(){return J.a.confirmationMessage}}),!Object(E.s)(e.props.errors)&&s.a.createElement(u.a,{negative:!0},s.a.createElement(u.a.Header,null,"Error"),s.a.createElement("p",null,e.props.errors.message)),s.a.createElement(z.a,{applicantMs:a.values.applicantMs}),s.a.createElement(Z.a,{content:ae.form_tapasil}),s.a.createElement(X.a,{setFieldValue:a.setFieldValue,errors:a.errors,values:a.values}),s.a.createElement(M.a,{data:q.c?ae.form_step2_biratnagar:ae.form_step2,newMunicipal:oe.newMunicipal,setFieldValue:a.setFieldValue,errors:a.errors,values:a.values,wardOptions:e.state.wardOptions}),s.a.createElement(Y.a,{setFieldValue:a.setFieldValue,errors:a.errors,values:a.values,landPropsName:$,handleChange:a.handleChange}),s.a.createElement(Z.a,{content:ae.form_step5.heading}),s.a.createElement("div",{className:"frmCheckbox-wrap"},s.a.createElement("span",null,ae.form_step5.fieldName_1),Object.values(ae.form_step5.checkBox_option).map(function(e){return s.a.createElement("div",{className:"ui radio checkbox",key:e},s.a.createElement("input",{type:"radio",name:"purposeOfConstruction",value:e,defaultChecked:a.values.purposeOfConstruction===e,onChange:a.handleChange}),s.a.createElement("label",null,e))}),a.values.purposeOfConstruction===ae.form_step5.checkBox_option.option_3&&s.a.createElement(v.h,{name:"purposeOfConstructionOther",placeholder:"Additional Information...",setFieldValue:a.setFieldValue,error:a.errors.purposeOfConstructionOther})),s.a.createElement("div",{className:"frmCheckbox-wrap"},q.c?s.a.createElement(D.b,{values:a.values,errors:a.errors,otherName:"constructionTypeOther",setFieldValue:a.setFieldValue,fieldLabel:ae.form_step5.fieldName_2}):s.a.createElement(D.a,{fieldLabel:ae.form_step5.fieldName_2})),s.a.createElement(C.d,{label:ae.form_step5.fieldName_3,name:"oldMapDate",setFieldValue:a.setFieldValue,value:a.values.oldMapDate,error:a.errors.oldMapDate}),s.a.createElement("div",{className:"frmCheckbox-wrap"},s.a.createElement("span",null,ae.form_step5.fieldName_4),s.a.createElement(b.a,{name:"mohada",onChange:a.handleChange,value:ae.form_step5.checkBox_option3.option_1}),s.a.createElement(b.a,{name:"mohada",onChange:a.handleChange,value:ae.form_step5.checkBox_option3.option_2}),s.a.createElement(b.a,{name:"mohada",onChange:a.handleChange,value:ae.form_step5.checkBox_option3.option_3}),s.a.createElement(b.a,{name:"mohada",onChange:a.handleChange,value:ae.form_step5.checkBox_option3.option_4})),s.a.createElement("div",{className:"frmCheckbox-wrap"},s.a.createElement("span",null,ae.form_step5.fieldName_5),Object.values(ae.form_step5.checkBox_option4).map(function(e){return s.a.createElement("div",{key:e,className:"ui radio checkbox"},s.a.createElement("input",{type:"radio",name:"constructionFinishing",value:e,defaultChecked:a.values.constructionFinishing===e,onChange:a.handleChange}),s.a.createElement("label",null,e))}),a.values.constructionFinishing===ae.form_step5.checkBox_option4.option_6&&s.a.createElement(v.h,{name:"constructionFinishingOther",placeholder:"Additional Information...",setFieldValue:a.setFieldValue,error:a.errors.constructionFinishingOther})),s.a.createElement(Z.a,{content:ae.form_step6.heading}),s.a.createElement("div",{className:"frmCheckbox-wrap"},Object.values(ae.form_step6.checkBox_option).map(function(e){return s.a.createElement("div",{key:e,className:"ui radio checkbox"},s.a.createElement("input",{type:"radio",name:"dhalNikasArrangement",value:e,defaultChecked:a.values.dhalNikasArrangement===e,onChange:a.handleChange}),s.a.createElement("label",null,e))}),a.values.dhalNikasArrangement===ae.form_step6.checkBox_option.option_3&&s.a.createElement("div",{className:"sewageMgmt"},s.a.createElement("span",null,ae.form_step6.fieldName_1),s.a.createElement(x.a,{name:"dhalNikasArrangementOther",onChange:a.handleChange,value:a.values.dhalNikasArrangementOther,setFieldValue:a.setFieldValue,options:ee,nameUnit:"dhalUnit",unitvalue:a.values.dhalUnit}))),s.a.createElement(Z.a,{content:ae.form_step7.heading}),s.a.createElement("div",{className:"frmCheckbox-wrap"},Object.values(ae.form_step7.checkBox_option).map(function(e){return s.a.createElement("div",{key:e,className:"ui radio checkbox"},s.a.createElement("input",{type:"radio",name:"foharArrangement",value:e,defaultChecked:a.values.foharArrangement===e,onChange:a.handleChange}),s.a.createElement("label",null,e))}),a.values.foharArrangement===ae.form_step7.checkBox_option.option_3&&s.a.createElement(v.h,{name:"foharArrangementOther",placeholder:"Additional Information...",setFieldValue:a.setFieldValue,error:a.errors.foharArrangementOther})),s.a.createElement(Z.a,{content:ae.form_step8.heading}),s.a.createElement("div",{className:"frmCheckbox-wrap"},s.a.createElement("span",null,ae.form_step8.fieldName_1),Object.values(ae.form_step8.checkBox_option).map(function(e){return s.a.createElement(P.a,{key:e,name:"pipeline",option:e})})),a.values.pipeline===ae.form_step8.checkBox_option.option_1?s.a.createElement("div",{className:"pipelineMgmt"},s.a.createElement("span",null,ae.form_step8.fieldName_2),s.a.createElement(x.a,{name:"pipelineDistance",onChange:a.handleChange,value:a.values.pipelineDistance,setFieldValue:a.setFieldValue,options:ee,nameUnit:"pipelineUnit",unitvalue:a.values.pipelineUnit})):null,s.a.createElement("div",{className:"frmCheckbox-wrap"},s.a.createElement("span",null,ae.form_step8.fieldName_3),Object.values(ae.form_step8.checkBox_option2).map(function(e){return s.a.createElement(P.a,{key:e,name:"doPipelineConnection",option:e})})),s.a.createElement(Z.a,{content:ae.form_step9.heading}),s.a.createElement("div",{className:"frmCheckbox-wrap"},s.a.createElement("span",null,ae.form_step9.fieldName_1),Object.values(ae.form_step9.checkBox_option).map(function(e){return s.a.createElement(P.a,{key:e,name:"isHighTensionLine",option:e})})),s.a.createElement("div",{className:"frmCheckbox-wrap"},s.a.createElement("span",null,ae.form_step9.fieldName_2),Object.values(ae.form_step9.checkBox_option).map(function(e){return s.a.createElement(P.a,{key:e,name:"isLowTensionLine",option:e})})),s.a.createElement("div",{className:"elecMgmt"},s.a.createElement("span",null,ae.form_step9.fieldName_3),s.a.createElement(x.a,{name:"isHighTensionLineDistance",onChange:a.handleChange,value:a.values.isHighTensionLineDistance,setFieldValue:a.setFieldValue,options:ee,nameUnit:"highTensionLineUnit",unitvalue:a.values.highTensionLineUnit})),s.a.createElement("br",null),s.a.createElement("div",null,s.a.createElement("b",null,ae.form_tallaBibarab)),s.a.createElement("div",{style:{marginLeft:"30px"}},s.a.createElement("div",null,s.a.createElement("b",null,ae.form_step9.floor_details.block,B.h.map(function(t){return s.a.createElement(s.a.Fragment,{key:t.key},s.a.createElement("div",{className:"ui radio checkbox ".concat("prabidhik")},s.a.createElement("input",{type:"radio",value:t.value,checked:e.state.hasBlocks===t.value,onChange:function(n){a.setFieldValue("floor",[]),e.setState({hasBlocks:t.value})}}),s.a.createElement("label",null,t.value)))}))),e.state.hasBlocks===B.h[0].value?s.a.createElement(T.a,{setFieldValue:a.setFieldValue,handleChange:a.handleChange,values:a.values,errors:a.errors}):s.a.createElement(d.a,{celled:!0,compact:!0,collapsing:!0,striped:!0,style:{maxWidth:"1000px"}},s.a.createElement(S.b,{errors:a.errors,values:a.values,setFieldValue:a.setFieldValue}),s.a.createElement(S.a,a))),s.a.createElement("div",null,s.a.createElement(Z.a,{content:ae.form_step10.heading}),a.errors.member&&s.a.createElement("span",{className:"tableError"},a.errors.member),s.a.createElement("div",{style:{marginLeft:"30px"},className:"field ".concat(a.errors.member?"error":"")},s.a.createElement(d.a,{celled:!0,compact:!0,collapsing:!0},s.a.createElement(d.a.Header,null,s.a.createElement(d.a.Row,{textAlign:"center"},Object.keys(t.table_heading).map(function(e){return s.a.createElement(d.a.HeaderCell,{key:e},t.table_heading[e])}))),s.a.createElement(d.a.Body,null,Object.keys(t.table_subheading).map(function(e){return n++,s.a.createElement(d.a.Row,{key:e},s.a.createElement(h.a,null,s.a.createElement("label",{htmlFor:"member[".concat(n-1,"].member")},t.table_subheading[e]),s.a.createElement("input",{id:"member[".concat(n-1,"].member"),name:"member[".concat(n-1,"].member"),hidden:!0,readOnly:!0,value:"member[".concat(n-1,"].member")})),s.a.createElement(h.a,null,s.a.createElement(v.h,{style:{width:"180px"},name:"member[".concat(n-1,"].memberName"),setFieldValue:a.setFieldValue,value:Object(c.d)(a.values,"member[".concat(n-1,"].memberName")),error:Object(c.d)(a.errors,"member[".concat(n,"].memberName"))})),s.a.createElement(h.a,null,s.a.createElement(v.h,{style:{width:"180px"},name:"member[".concat(n-1,"].relation"),setFieldValue:a.setFieldValue,value:Object(c.d)(a.values,"member[".concat(n-1,"].relation")),error:Object(c.d)(a.errors,"member[".concat(n,"].relation"))})))}))))),s.a.createElement(H.a,{setFieldValue:a.setFieldValue,values:a.values,errors:a.errors,wardOptions:e.state.wardOptions}),s.a.createElement(W.a,null,s.a.createElement(p.a.Group,{inline:!0,style:{paddingBottom:10,paddingTop:10}},s.a.createElement(p.a.Field,null,s.a.createElement("label",null,s.a.createElement("b",null,"Latitude")),s.a.createElement(c.a,{name:"lat",type:"number"})),s.a.createElement(p.a.Field,null,s.a.createElement("label",null,s.a.createElement("b",null,"Longitude")),s.a.createElement(c.a,{name:"lng",type:"number"})),s.a.createElement(p.a.Button,{primary:!0,icon:"search",content:K.c.find,onClick:e.handleFindOnMap.bind(e,a.values.lat,a.values.lng)}))),s.a.createElement(A.a,{handleMarkerClick:function(t){t.latlng&&(a.setFieldValue("lat",t.latlng.lat),a.setFieldValue("lng",t.latlng.lng),e.handleMarkerClick(t))},marker:e.state.marker,position:e.state.mapPosition}),s.a.createElement(f.a,{primary:!0,content:"Save",type:"button",disabled:e.state.saved,onClick:function(e){!Object(E.s)(a.errors)&&Object.keys(a.errors).length>0?function(){for(var e=document.forms[0],t=function(t){if(Object.keys(a.errors).some(function(a){return e[t].name.includes(a)}))return e[t].focus(),"break"},n=0;n<e.length;n++){if("break"===t(n))break}}():a.handleSubmit(e)}}),e.state.saved&&s.a.createElement(f.a,{primary:!0,basic:!0,onClick:e.handleModalOpen},"UploadFile"))}})))}}]),t}(i.Component),ie={postFormData:k.b,postFileValidation:F.g,postNibedakPhoto:k.c,getFileCategories:F.b,setPermit:O.k,getTaskList:O.f};a.default=Object(_.b)(function(e){return{fileCategories:e.root.formData.fileCategories,formData:e.root.formData,success:e.root.formData.success,errors:e.root.ui.errors,loading:e.root.ui.loading}},ie)(le)}}]);
//# sourceMappingURL=15.5bfa311a.chunk.js.map